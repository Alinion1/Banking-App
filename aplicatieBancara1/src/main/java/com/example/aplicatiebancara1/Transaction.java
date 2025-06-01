package com.example.aplicatiebancara1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; // "Depunere", "Retragere", "Transfer"
    private double amount;
    private LocalDateTime timestamp;
    private String details;

    public Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    // Getteri
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return timestamp.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f RON - %s - %s",
                type, amount, getFormattedDate(), details);
    }
}