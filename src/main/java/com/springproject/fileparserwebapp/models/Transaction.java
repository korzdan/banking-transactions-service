package com.springproject.fileparserwebapp.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Setter
@Getter
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transaction_id")
    private UUID transactionId;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "amount")
    private long amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private User user;


    public Transaction() {}

    public Transaction(UUID transactionId, User user, UUID userId, Timestamp timestamp, long amount, String currency, String status) {
        this.transactionId = transactionId;
        this.user = user;
        this.userId = userId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }
}
