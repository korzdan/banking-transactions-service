import React, {useEffect, useState} from 'react';
import "../ErrorTable/ErrorTable.css";
import {useLocation} from "react-router";

const UserErrors = () => {

    const data = useLocation();
    const [errors, setErrors] = useState([]);

    useEffect(() => {
        setErrors(data.state);
    }, []);

    return (
        <div className="outer-table">
            <div className="scroll-div">
                <table>
                    <thead>
                    <tr>
                        <th>Message</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        errors.map((error) => (
                            <tr key={error.id}>
                                <td>{error.message}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UserErrors;