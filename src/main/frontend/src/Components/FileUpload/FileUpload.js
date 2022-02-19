import React, {useState} from 'react';
import axios from "axios";
import {getToken} from "../../Utils/Common";

const FileUpload = () => {

    const [multipleFiles, setMultipleFiles] = useState('');
    const [results, setResults] = useState('');

    const MultipleFileChange = (e) => {
        setMultipleFiles(e.target.files);
    }

    const UploadMultipleFiles = async () => {
        const formData = new FormData();
        for (let i = 0; i < multipleFiles.length; i++) {
            formData.append('files', multipleFiles[i]);
        }

        await axios.post("http://localhost:8080/upload", formData, {
            headers: {
                'Authorization': getToken()
            }
        }).then(response => {
            setResults(response.data);
        }).catch(error => {
            if (error.response.status === 406) {
                setResults(error.response.data);
            }
        })
    }

    return (
        <form>
            <div className="file-card">
                <input type="file" onChange={(e) => MultipleFileChange(e)} multiple/>
                <button type="button" onClick={() => UploadMultipleFiles()}>Submit</button>
                <div id="file-upload-results">
                    {results}
                </div>
            </div>
        </form>
    );
};

export default FileUpload;