import React, {useEffect, useState} from "react";
import {useKeycloak} from "@react-keycloak/web";
import {useNavigate} from "react-router-dom";

type CurrentTenant = {
    username: string;
};
type Application = {
    id: number;
    type: string;
    scale: number;
    durationInDays: number;
    accepted: boolean | null;
};

const getCurrentTenant = (callback: (x: any) => void, token?: string) => {
    fetch("http://localhost:8080/current-tenant", {
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

const postApplications = (body: any, callback: () => void, token?: string) => {
    fetch("http://localhost:8080/applications", {
        method: "POST",
        headers: {Authorization: "Bearer " + token, "Content-Type": "application/json",},
        body: JSON.stringify(body)
    })
        .then(callback)
        .catch(console.error)
}

export const TenantPage = () => {
    const {keycloak} = useKeycloak();
    const navigate = useNavigate();
    const [currentTenant, setCurrentTenant] = useState<CurrentTenant>();
    const [applications, setApplications] = useState<Application[]>();

    const updateScreen = () => {
        getCurrentTenant(setCurrentTenant, keycloak.token);
        getApplications(setApplications);
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

    if (!currentTenant || !applications) {
        return <h1>Loading...</h1>;
    }

    return (
        <>
            <h1>
                {currentTenant.username}
            </h1>
            <button onClick={() => keycloak.logout()}>Logout</button>
            <table>
                <thead>
                <tr>
                    <th>type</th>
                    <th>scale</th>
                    <th>duration in days</th>
                    <th>is accepted</th>
                </tr>
                </thead>
                <tbody>
                {applications.map((a) => (
                    <tr key={"tr_" + a.id}>
                        <td>{a.type}</td>
                        <td>{a.scale}</td>
                        <td>{a.durationInDays}</td>
                        <td>{a.accepted?.toString() || "-"}</td>
                    </tr>
                ))}
                <tr>
                    <td><input id={"new-type"}/></td>
                    <td><input id={"new-scale"} type={"number"} min={0}/></td>
                    <td><input id={"new-duration"} type={"number"} min={0}/></td>
                    <td>
                        <button onClick={() => {
                            // @ts-ignore
                            const newType: string = document.getElementById("new-type")?.value;
                            // @ts-ignore
                            const newScale: number = document.getElementById("new-scale")?.value;
                            // @ts-ignore
                            const newDuration: number = document.getElementById("new-duration")?.value;
                            postApplications({
                                type: newType,
                                scale: newScale,
                                durationInDays: newDuration
                            }, updateScreen, keycloak.token)
                        }}>
                            add new
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </>
    );
};
