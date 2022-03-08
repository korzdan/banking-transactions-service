import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/Common";
import "../TransactionCard/TransactionCard.css"

const TransactionCard = (props) => {

    const [transaction, setTransaction] = useState([]);

    useEffect(() => {
        axios.get(props.url, {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setTransaction(response.data);
        });
    }, []);

    return (
        <div className="outer-card">
            <div className="transaction-card">
                <div className="card-field">Transaction ID: {transaction.transactionId}</div>
                <div className="card-field">User ID: {transaction.userId}</div>
                <div className="card-field">Status: {transaction.status}</div>
                <div className="card-field">Amount: {transaction.amount}</div>
                <div className="card-field">Currency: {transaction.currency}</div>
                <div className="card-field">Timestamp: {transaction.timestamp}</div>
            </div>
        </div>
    );
};

export default TransactionCard;