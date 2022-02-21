import React, {useState} from 'react';
import axios from "axios";
import {setToken} from "../../Utils/Common";

const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin = () => {
        setError(null);
        axios.post("http://localhost:8080/auth/login", {
            username: username,
            password: password
        }).then(response => {
            setToken(response.data.token);
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
        <form>
            <div className="form-inner">
                <h2>Welcome to login page! You need to be authenticated to use our app.</h2>
                {(error !== "") && (<div className="login-error">{error}</div>)}
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input id="username" type="text" value={username}
                        onChange={e => setUsername(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input type="password" id="password" value={password}
                           onChange={e => setPassword(e.target.value)}/>
                </div>
                <input type="button" value="Login" onClick={handleLogin}/>
            </div>
        </form>
    );
};

export default Login;