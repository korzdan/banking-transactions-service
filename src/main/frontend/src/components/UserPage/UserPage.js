import React, {useEffect, useState} from 'react';
import "../UserPage/UserPage.css"
import {useLocation} from "react-router";
import {Link} from "react-router-dom";

const UserPage = () => {

    const data = useLocation();
    const [user, setUser] = useState('');

    useEffect(() => {
        setUser(data.state);
    }, []);

    return (
        <div className="user-page-outer">
            <div className="username">{user.username}</div>
            <div className="user-role">{user.role}</div>
            <div className="user-status">{user.status}</div>
            <div className="user-transactions">
                <Link to={{
                    pathname: `${user.id}/transactions`,
                    state: user.transactions
                }}>Transactions</Link>
            </div>
            <div className="user-files">
                <Link to={{
                    pathname: `${user.id}/files`,
                    state: user.files
                }}>Files</Link>
            </div>
            <div className="user-errors">
                <Link to={{
                    pathname: `${user.id}/errors`,
                    state: user.errors
                }}>Errors</Link>
            </div>
        </div>
    );
};

export default UserPage;