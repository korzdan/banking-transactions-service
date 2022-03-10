import React, {useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/Common";
import "../UserRegistration/UserRegistration.css";
import {useHistory} from "react-router";
import TokenPopup from "../TokenPopup/TokenPopup";

const UserRegistration = () => {

    const [popup, setPopup] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('MANAGER');
    const [error, setError] = useState('');
    const history = useHistory();

    const handleRegister = async () => {
        if (validateFields()) {
            await axios.post("http://localhost:8080/users/register", {
                username: username,
                password: password,
                role: role
            }, {
                headers: {
                    'Authorization': getToken()
                }
            }).then(response => {
                history.push('/transactions');
            }).catch(error => {
                if (error.response.status === 409) {
                    setError(error.response.data);
                }
                if (error.response.status === 403) {
                    setPopup(true);
                }
            });
        } else {
            setError("No fields should be empty.");
        }
    }

    const handleRadio = (event) => {
        setRole(event.target.value)
    }

    const validateFields = () => {
        return !(username.length === 0 || password.length === 0);
    }

    return (
        <div className="outer-from">
            <div className="container">
                <div className="title">Registration</div>
                {error && <div className="error">{error}</div>}
                <div className="main-data">
                    <div className="form-group">
                        <label htmlFor="username">Username:</label>
                        <input className="register" id="username" type="text" value={username}
                               onChange={e => setUsername(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password:</label>
                        <input className="register" type="password" id="password" value={password}
                               onChange={e => setPassword(e.target.value)}/>
                    </div>
                </div>
                <div className="role-details">
                    <div><input id="manager" type="radio" value="MANAGER" checked={role === 'MANAGER'}
                                onChange={handleRadio}/>
                        <label htmlFor="manager">Manager</label>
                    </div>

                    <div><input id="operator" type="radio" value="OPERATOR" checked={role === 'OPERATOR'}
                                onChange={handleRadio}/>
                        <label htmlFor="operator">Operator</label>
                    </div>
                </div>
                <input className="register-button" type="button" id="register" value="Register" onClick={handleRegister}/>

                <TokenPopup trigger={popup} setTrigger={setPopup}>
                    <div>
                        Your token is invalid! It's expired or token was changed. You need to
                        re-login to verify that everything is ok.
                    </div>
                </TokenPopup>
            </div>
        </div>
    );
};

export default UserRegistration;