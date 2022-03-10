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
            <div className="user-page-inner">
                <div className="user-data">
                    <div className="username">Username: {user.username}</div>
                    <div className="user-role">Role: {user.role}</div>
                    <div className="user-status">Status: {user.status}</div>
                </div>
                <div className="user-actions-info">
                    <div className="user-transactions">
                        <Link className="user-link" to={{
                            pathname: `${user.id}/transactions`,
                            state: user.transactions
                        }}>Transactions</Link>
                    </div>
                    <div className="user-files">
                        <Link className="user-link" to={{
                            pathname: `${user.id}/files`,
                            state: user.files
                        }}>Files</Link>
                    </div>
                    <div className="user-errors">
                        <Link className="user-link" to={{
                            pathname: `${user.id}/errors`,
                            state: user.errors
                        }}>Errors</Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default UserPage;