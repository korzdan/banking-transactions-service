import React from 'react';
import {useHistory} from "react-router";

const TokenPopup = (props) => {

    const history = useHistory();

    return (props.trigger) ? (
        <div>
            <div>
                <button onClick={() => {
                    props.setTrigger(false);
                    history.push("/login");
                }}>
                    Re-login
                </button>
                {props.children}
            </div>
        </div>
    ) : "";
};

export default TokenPopup;