import React, {useEffect, useState} from 'react';
import "../ErrorTable/ErrorTable.css";
import {useParams} from "react-router";
import axios from "axios";
import {getToken} from "../../utils/Common";

const UserErrors = () => {

    const params = useParams();
    const [errors, setErrors] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/users/${params.id}/errors`, {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setErrors(response.data);
        });
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