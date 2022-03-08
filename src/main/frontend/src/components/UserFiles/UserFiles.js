import React, {useEffect, useState} from 'react';
import "../ErrorTable/ErrorTable.css";
import {useLocation} from "react-router";

const UserFiles = () => {

    const data = useLocation();
    const [files, setFiles] = useState([]);

    useEffect(() => {
        setFiles(data.state);
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