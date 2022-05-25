import React, {useEffect, useState} from "react";
import {useKeycloak} from "@react-keycloak/web";
import {useNavigate} from "react-router-dom";

type CurrentDispatcher = {
    username: string;
};
type Application = {
    id: number;
    type: string;
    scale: number;
    durationInDays: number;
    accepted: boolean | null | undefined;
};


const getCurrentDispatcher = (callback: (x: any) => void, token?: string) => {
    fetch("http://localhost:8080/current-dispatcher", {
        headers: {Authorization: "Bearer " + token},
    })
        .then((r) => r.json())
        .then(callback)
        .catch(console.error)
}

const getApplications = (callback: (x: any) => void) => {
    fetch("http://localhost:8080/applications")
        .then((r) => r.json())
        .then(callback)
        .catch(console.error)
}

const putDisable = (body: any, callback: () => void, token?: string) => {
    fetch("http://localhost:8080/disable", {
        method: "PUT",
        headers: {Authorization: "Bearer " + token, "Content-Type": "application/json",},
        body: JSON.stringify(body)
    })
        .then(callback)
        .catch(console.error)
}

const putEnable = (body: any, callback: () => void, token?: string) => {
    fetch("http://localhost:8080/enable", {
        method: "PUT",
        headers: {Authorization: "Bearer " + token, "Content-Type": "application/json",},
        body: JSON.stringify(body)
    })
        .then(callback)
        .catch(console.error)
}

export const DispatcherPage = () => {
    const {keycloak} = useKeycloak();
    const navigate = useNavigate();
    const [currentDispatcher, setCurrentDispatcher] = useState<CurrentDispatcher>();
    const [applications, setApplications] = useState<Application[]>();

    const updateScreen = () => {
        getCurrentDispatcher(setCurrentDispatcher, keycloak.token)
        getApplications(setApplications)
    };

    useEffect(() => {
        setTimeout(() => {
            if (!keycloak.authenticated) {
                navigate("/");
                return;
            }
            updateScreen();
        }, 300);
    }, []);

    if (!currentDispatcher || !applications) {
        return <h1>Loading...</h1>;
    }

    return (
        <>
            <h1>
                {currentDispatcher.username}
            </h1>
            <button onClick={() => keycloak.logout()}>Logout</button>
            <h3>waiting to accept</h3>
            <table>
                <thead>
                <tr>
                    <th>id</th>
                    <th>type</th>
                    <th>scale</th>
                    <th>duration in days</th>
                    <th>action</th>
                </tr>
                </thead>
                <tbody>
                {applications.filter(a => a.accepted === null).map(a => (
                    <tr key={"tr_" + a.id}>
                        <td>{a.id}</td>
                        <td>{a.type}</td>
                        <td>{a.scale}</td>
                        <td>{a.durationInDays}</td>
                        <td>
                            <button
                                id={"enable_" + a.id}
                                onClick={e => {
                                    // @ts-ignore
                                    const id: number = e.target.id.substring(7);
                                    putEnable({id: id}, updateScreen, keycloak.token)
                                }}
                            >
                                enable
                            </button>
                            <button
                                id={"disable_" + a.id}
                                onClick={e => {
                                    // @ts-ignore
                                    const id: number = e.target.id.substring(8);
                                    putDisable({id: id}, updateScreen, keycloak.token)
                                }}
                            >
                                disable
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <h3>accepted</h3>
            <table>
                <thead>
                <tr>
                    <th>id</th>
                    <th>type</th>
                    <th>scale</th>
                    <th>duration in days</th>
                </tr>
                </thead>
                <tbody>
                {applications.filter(a => a.accepted === true).map(a => (
                    <tr key={"tr_" + a.id}>
                        <td>{a.id}</td>
                        <td>{a.type}</td>
                        <td>{a.scale}</td>
                        <td>{a.durationInDays}</td>
                    </tr>
                ))}
                </tbody>
            </table>
            <h3>declined</h3>
            <table>
                <thead>
                <tr>
                    <th>id</th>
                    <th>type</th>
                    <th>scale</th>
                    <th>duration in days</th>
                </tr>
                </thead>
                <tbody>
                {applications.filter(a => a.accepted === false).map(a => (
                    <tr key={"tr_" + a.id}>
                        <td>{a.id}</td>
                        <td>{a.type}</td>
                        <td>{a.scale}</td>
                        <td>{a.durationInDays}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </>
    );
};
