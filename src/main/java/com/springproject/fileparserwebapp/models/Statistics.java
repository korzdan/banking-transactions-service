package com.springproject.fileparserwebapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Statistics {
    private Long totalNumberOfTransactions;
    private Long numberOfSuccessful;
    private Long numberOfFailed;
    private Transaction minTransaction;
    private Transaction maxTransaction;

    public String toString() {
        return "Total number of transactions: " + totalNumberOfTransactions +
                " + where successful: " + numberOfSuccessful +
                " failed: " + numberOfFailed +
                "Min transaction: " + minTransaction +
                "Max transaction: " + maxTransaction;
    }
}
