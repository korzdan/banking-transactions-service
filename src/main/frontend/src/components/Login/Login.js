import React, {useEffect, useState} from 'react';
import axios from "axios";
import {setToken} from "../../utils/Common";
import "../Login/Login.css"

const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        setToken('');
    }, []);

    const handleLogin = () => {
        setError(null);
        axios.post("http://localhost:8080/auth/login", {
            username: username,
            password: password
        }).then(response => {
            setToken(response.data.token);
            props.history.push("/upload");
        }).catch(error => {
            if (error.response.status === 401) {
                setError(error.response.data.message);
            } else {
                setError("Something went wrong. Please try again later.");
            }
        });
    }

    return (
        <div className="outer-form">
        <form className="login-form">
            <div className="form-inner">
                <h2>Please login, to use our application!</h2>
                {(error !== "") && (<div className="login-error">{error}</div>)}
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input className="login" id="username" type="text" value={username}
                        onChange={e => setUsername(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input className="login" type="password" id="password" value={password}
                           onChange={e => setPassword(e.target.value)}/>
                </div>
                <input className="login" type="button" id="login" value="Login" onClick={handleLogin}/>
            </div>
        </form>
        </div>
    );
};

export default Login;