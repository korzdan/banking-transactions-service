import React, {useEffect, useState} from 'react';
import "../ErrorTable/ErrorTable.css";
import axios from "axios";
import {getToken} from "../../utils/Common";
import {useParams} from "react-router";

const UserTransactions = () => {

    const params = useParams();
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/users/${params.id}/transactions`, {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setTransactions(response.data);
        });
    }, []);

    return (
        <div className="outer-table">
            <div className="scroll-div">
                <table>
                    <thead>
                    <tr>
                        <th>TransactionID</th>
                        <th>UserID</th>
                        <th>Status</th>
                        <th>Amount</th>
                        <th>Currency</th>
                        <th>Timestamp</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        transactions.map((transaction) => (
                            <tr key={transaction.id}>
                                <td>{transaction.transactionId}</td>
                                <td>{transaction.userId}</td>
                                <td>{transaction.status}</td>
                                <td>{transaction.amount}</td>
                                <td>{transaction.currency}</td>
                                <td>{transaction.timestamp}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UserTransactions;