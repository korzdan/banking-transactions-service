import React, {useState} from 'react';
import {useHistory} from "react-router";

const TransactionSearch = () => {

    const [id, setId] = useState(0);
    const history = useHistory('');

    const handleCard = () => {
      history.push("search/" + id);
    }

    return (
        <div>
            <input type="text" onChange={e => setId(e.target.value)}/>
            <input type="button" onClick={handleCard}/>
        </div>
    );
};

export default TransactionSearch;