import React, {useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/Common";
import "../FileUpload/FileUpload.css";
import file_upload from '../../assets/file_upload.png';
import TokenPopup from "../TokenPopup/TokenPopup";

const FileUpload = () => {

    const [multipleFiles, setMultipleFiles] = useState('');
    const [results, setResults] = useState('');
    const [popup, setPopup] = useState(false);
    const [disable, setDisable] = useState(false);
    const [zeroFilesNum, setZeroFilesNum] = useState('');

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
            if (error.response.status === 403 || error.response.status === 401) {
                setPopup(true);
                setDisable(true);
            }
            if (error.response.status === 400) {
                setZeroFilesNum("At least one file should be selected!");
            }
            if (error.response.status === 409) {
                setResults("The uploaded file is too large.")
            }
        })
    }

    return (
        <div className="main_div">
            <label className="file-upload-label">
                <img src={file_upload} alt="file_upload" id="file_upload"/>
                <input disabled={disable} type="file" onChange={(e) => {
                    MultipleFileChange(e);
                    setZeroFilesNum('');
                }} multiple/>
                Choose Files to Upload
            </label>
            {multipleFiles.length!==0 && <div id="files_number">{multipleFiles.length} files are chosen</div>}
            {zeroFilesNum && <div id="files_number">{zeroFilesNum}</div>}
            <button type="button" disabled={disable} onClick={() => UploadMultipleFiles()}>SUBMIT</button>
            {results && <div id="file_upload_results">{results}</div>}

            <TokenPopup trigger={popup} setTrigger={setPopup}>
                <div>
                    Your token is invalid! It's expired or token was changed. You need to
                    re-login to verify that everything is ok.
                </div>
            </TokenPopup>
        </div>
    );
};

export default FileUpload;