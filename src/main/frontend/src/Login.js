import React, {useState} from 'react';
import {useHistory} from "react-router-dom";
import axios from "axios";
import {setUserSession} from "./Utils/Common";

const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    const handleLogin = () => {
        setError(null);
        axios.post("http://localhost:8080/auth/login", {
            username: username,
            password: password
        }).then(response => {
            setUserSession(response.data.token);
            props.history.push("/success");
            console.log(response);
        }).catch(error => {
            if (error.response.status === 401) {
                setError(error.response.data.message);
            } else {
                setError("Something went wrong. Please try again later.");
            }
        });
    }

    return (
        <div>
            Welcome to Login page!<br/>
            <div>
                Username<br/>
                <input type="text" value={username}
                    onChange={e => setUsername(e.target.value)}/>
            </div>
            <div>
                Password<br/>
                <input type="password" value={password}
                       onChange={e => setPassword(e.target.value)}/>
            </div>
            {error && <div className="error">{error}</div>}
            <input type="button" value="login"
                onClick={handleLogin}/>
        </div>
    );
};

export default Login;