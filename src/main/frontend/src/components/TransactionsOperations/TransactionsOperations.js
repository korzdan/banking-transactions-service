import React from 'react';
import "../TransactionsOperations/TransactionsOperations.css";
import {useHistory} from "react-router";

const TransactionsOperations = () => {

    const history = useHistory();

    return (
        <div className="transactions">
            <div className="transactions-buttons">
                <button onClick={() => history.push("/transactions")}>All transactions</button>
                <button onClick={() => history.push("/transactions/search")}>Find transaction</button>
                <button onClick={() => history.push("/transactions/top")}>Top transactions</button>
                <button onClick={() => history.push("/transactions/max")}>Max transaction</button>
                <button onClick={() => history.push("/transactions/min")}>Min transaction</button>
                <button onClick={() => history.push("/transactions/statistics")}>Statistics</button>
            </div>
        </div>
    );
};

export default TransactionsOperations;