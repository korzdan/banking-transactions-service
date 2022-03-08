import React, {useEffect, useState} from 'react';
import "../FoundTransaction/FoundTransaction.css";
import {useParams} from "react-router";
import axios from "axios";
import {getToken} from "../../utils/Common";

const FoundTransaction = () => {

    const {id} = useParams();
    const [transaction, setTransaction] = useState('');

    useEffect(() => {
        axios.get(`http://localhost:8080/transactions/${id}`, {
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

export default FoundTransaction;