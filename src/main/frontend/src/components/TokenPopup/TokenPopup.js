import React from 'react';
import {useHistory} from "react-router";
import "./TokenPopup.css"

const TokenPopup = (props) => {

    const history = useHistory();

    return (props.trigger) ? (
        <div id="main-popup">
            {props.children}
            <button id="re-login" onClick={() => {
                props.setTrigger(false);
                history.push("/login");
            }}>
                Re-login
            </button>
        </div>
    ) : "";
};

export default TokenPopup;