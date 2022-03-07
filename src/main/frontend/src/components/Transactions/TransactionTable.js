import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../../utils/Common";
import "../Transactions/TransactionTable.css"

const TransactionTable = (props) => {
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        axios.get(props.url, {
            headers : {
                'Authorization' : getToken()
            }
        }).then(response => {
            setTransactions(response.data);
            console.log(response.data);
        });
    }, []);


    return (
        <div className="transactions-table">
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

export default TransactionTable;