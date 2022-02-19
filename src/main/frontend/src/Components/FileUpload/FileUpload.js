import React, {useState} from 'react';
import axios from "axios";
import {getToken} from "../../Utils/Common";
import "../FileUpload/FileUpload.css";
import file_upload from '../../Assets/file_upload.png';

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
            if (error.response.status === 403) {

            }
        })
    }

    return (
        <div className="main_div">
            <label>
                <img src={file_upload} alt="file_upload" id="file_upload"/>
                <input type="file" onChange={(e) => MultipleFileChange(e)} multiple/>
                CHOOSE FILES TO UPLOAD
            </label>
            {multipleFiles.length!==0 && <div id="files_number">{multipleFiles.length} files are chosen</div>}
            <button type="button" onClick={() => UploadMultipleFiles()}>SUBMIT</button>
            {results && <div id="file_upload_results">{results}</div>}
        </div>
    );
};

export default FileUpload;