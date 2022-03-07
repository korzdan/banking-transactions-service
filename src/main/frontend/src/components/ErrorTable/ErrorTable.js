import React, {useEffect, useState} from 'react';
import "./ErrorTable.css"
import axios from "axios";
import {getToken} from "../../utils/Common";

const ErrorTable = () => {

    const [errors, setErrors] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/errors", {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setErrors(response.data);
            console.log(response.data);
        });
    }, []);


    return (
        <div className="error-table">
            <div className="scroll-div">
            <table>
                <thead>
                    <tr>
                        <th>User-id</th>
                        <th>Message</th>
                    </tr>
                </thead>
                <tbody>
                {
                    errors.map((error) => (
                        <tr key={error.id}>
                            <td>{error.id}</td>
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

export default ErrorTable;