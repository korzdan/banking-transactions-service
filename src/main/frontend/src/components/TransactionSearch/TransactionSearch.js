import React, {useState} from 'react';
import {useHistory} from "react-router";
import "../TransactionSearch/TransactionSearch.css";

const TransactionSearch = () => {

    const [id, setId] = useState(0);
    const history = useHistory('');

    const handleCard = () => {
      history.push("search/" + id);
    }

    return (
        <div className="outer-finder">
            <input id="find-field" type="text" onChange={e => setId(e.target.value)}/>
            <button id="find-button" onClick={handleCard}>Find</button>
        </div>
    );
};

export default TransactionSearch;