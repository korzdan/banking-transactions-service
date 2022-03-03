package com.springproject.fileparserwebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
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
    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "users_transactions",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount && Objects.equals(id, that.id)
                && Objects.equals(transactionId, that.transactionId)
                && Objects.equals(userId, that.userId)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(currency, that.currency)
                && Objects.equals(status, that.status)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionId, userId, timestamp, amount, currency, status, user);
    }
}
