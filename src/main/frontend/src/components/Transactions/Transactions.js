import React from 'react';
import "../Transactions/Transactions.css";
import {useHistory} from "react-router";

const Transactions = () => {

    const history = useHistory();

    return (
        <div className="transactions">
            <div className="transactions-buttons">
                <button onClick={() => history.push("/transactions/all")}>All transactions</button>
                <button onClick={() => history.push("/transactions/:id")}>User transactions</button>
                <button onClick={() => history.push("/transactions/top")}>Top transactions</button>
                <button onClick={() => history.push("/transactions/max")}>Max transaction</button>
                <button onClick={() => history.push("/transactions/min")}>Min transaction</button>
                <button onClick={() => history.push("/transactions/statistics")}>Statistics</button>
            </div>
        </div>
    );
};

export default Transactions;