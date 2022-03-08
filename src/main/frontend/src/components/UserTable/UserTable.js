import React, {useEffect, useState} from 'react';
import "../UserTable/UserTable.css";
import axios from "axios";
import {getToken} from "../../utils/Common";
import {Link} from "react-router-dom";

const UserTable = () => {

    const [users, setUsers] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/users", {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setUsers(response.data);
            console.log(response.data);
        });
    }, []);

    return (
        <div className="user-table">
            <div className="scroll-div">
                <table>
                    <thead>
                    <tr>
                        <th>Username</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        users.map((user) => (
                            <tr key={user.id}>
                                <td><Link to={{
                                    pathname: `/users/${user.id}`,
                                    state: user
                                }}>
                                    {user.username}
                                </Link></td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UserTable;