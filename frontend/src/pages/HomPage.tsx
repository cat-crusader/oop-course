import React, {useEffect} from "react";
import {useKeycloak} from "@react-keycloak/web";
import {useNavigate} from "react-router-dom";

export const Home = () => {
    const {keycloak} = useKeycloak();
    const navigate = useNavigate();

    useEffect(() => {
        setTimeout(() => {
            const roles = keycloak.realmAccess?.roles;
            if (!roles) {
                keycloak.login();
            } else if (roles.includes("tenant")) {
                navigate("tenant");
            } else if (roles.includes("dispatcher")) {
                navigate("dispatcher");
            } else {
                keycloak.login();
            }
        }, 500);
    }, []);

    return <h1>Loading...</h1>;
};
