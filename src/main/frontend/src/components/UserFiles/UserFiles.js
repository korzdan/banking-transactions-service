import React, {useEffect, useState} from 'react';
import "../ErrorTable/ErrorTable.css";
import {useParams} from "react-router";
import axios from "axios";
import {getToken} from "../../utils/Common";

const UserFiles = () => {

    const params = useParams();
    const [files, setFiles] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/users/${params.id}/files`, {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setFiles(response.data);
        });
    }, []);

    return (
        <div className="outer-table">
            <div className="scroll-div">
                <table>
                    <thead>
                    <tr>
                        <th>Filename</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        files.map((file) => (
                            <tr key={file.id}>
                                <td>{file.name}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UserFiles;