import React from "react";
import ReactDOM from "react-dom";
import "./Table.css";
import keycloak from "./Keycloak";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Home} from "./pages/HomPage";
import {TenantPage} from "./pages/TenantPage";
import {DispatcherPage} from "./pages/DispatcherPage";
import {ReactKeycloakProvider} from "@react-keycloak/web";

ReactDOM.render(
    <ReactKeycloakProvider authClient={keycloak}>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/tenant" element={<TenantPage/>}/>
                <Route path="/dispatcher" element={<DispatcherPage/>}/>
            </Routes>
        </BrowserRouter>
    </ReactKeycloakProvider>,
    document.getElementById("root")
);
