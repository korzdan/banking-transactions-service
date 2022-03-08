import React, {useEffect, useState} from 'react';
import "../Statistics/Statistics.css"
import axios from "axios";
import {getToken} from "../../utils/Common";

const Statistics = () => {

    const [statistics, setStatistics] = useState('');
    const [minTransaction, setMinTransaction] = useState('');
    const [maxTransaction, setMaxTransaction] = useState('');

    useEffect(() => {
        axios.get("http://localhost:8080/transactions/statistics", {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setStatistics(response.data);
            setMinTransaction(response.data.minTransaction);
            setMaxTransaction(response.data.maxTransaction);
        });
    }, []);

    return (
        <div className="main-statistics">
            <div className="general-statistics">
                <div className="transactions-number">Number of transactions: {statistics.totalNumberOfTransactions}</div>
                <div className="successful">successful transactions: {statistics.numberOfSuccessful}</div>
                <div className="failed">failed transactions: {statistics.numberOfFailed}</div>
            </div>
            <div className="top-transactions">
                    <div className="transaction">
                        <div className="card-field">Transaction ID: {minTransaction.transactionId}</div>
                        <div className="card-field">User ID: {minTransaction.userId}</div>
                        <div className="card-field">Status: {minTransaction.status}</div>
                        <div className="card-field">Amount: {minTransaction.amount}</div>
                        <div className="card-field">Currency: {minTransaction.currency}</div>
                        <div className="card-field">Timestamp: {minTransaction.timestamp}</div>
                    </div>
                    <div className="transaction">
                        <div className="card-field">Transaction ID: {maxTransaction.transactionId}</div>
                        <div className="card-field">User ID: {maxTransaction.userId}</div>
                        <div className="card-field">Status: {maxTransaction.status}</div>
                        <div className="card-field">Amount: {maxTransaction.amount}</div>
                        <div className="card-field">Currency: {maxTransaction.currency}</div>
                        <div className="card-field">Timestamp: {maxTransaction.timestamp}</div>
                    </div>
            </div>
        </div>
    );
};

export default Statistics;