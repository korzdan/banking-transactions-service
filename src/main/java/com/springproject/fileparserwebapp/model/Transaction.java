package com.springproject.fileparserwebapp.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "amount")
    private long amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "status")
    private String status;

    public Transaction() {}

    public Transaction(UUID transactionId, UUID userId, Timestamp timestamp,
                       long amount, String currency, String status) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
