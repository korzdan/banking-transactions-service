import React, {useEffect, useState} from 'react';
import "../ErrorTable/ErrorTable.css";
import {useLocation} from "react-router";

const UserTransactions = () => {

    const data = useLocation();
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        setTransactions(data.state);
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