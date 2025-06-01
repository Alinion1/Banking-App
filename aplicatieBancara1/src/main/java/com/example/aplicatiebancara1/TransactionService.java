package com.example.aplicatiebancara1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionService {
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    private static double balance = 1000.00; // Sold inițial, poate fi înlocuit cu o metodă de citire din fișier
    private static List<Transaction> transactions = new ArrayList<>();

    // Încărcăm tranzacțiile când se inițializează serviciul
    static {
        loadTransactions();
    }

    public static double getBalance() {
        return balance;
    }

    public static void setBalance(double newBalance) {
        balance = newBalance;
    }

    public static List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public static void addTransaction(Transaction transaction) {
        // Actualizăm balanța
        if (transaction.getType().equals("Depunere")) {
            balance += transaction.getAmount();
        } else if (transaction.getType().equals("Retragere") ||
                transaction.getType().equals("Trimite")) {
            balance -= transaction.getAmount();
        }

        // Adăugăm tranzacția la listă
        transactions.add(transaction);

        // Salvăm tranzacțiile în fișier
        saveTransactions();
    }

    private static void loadTransactions() {
        try {
            if (!new File(TRANSACTIONS_FILE).exists()) {
                return;
            }

            List<String> lines = Files.readAllLines(Paths.get(TRANSACTIONS_FILE));
            transactions.clear();

            // Reconstituim tranzacțiile din fișier
            // Format: tip,sumă,dată,detalii
            for (String line : lines) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    String type = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String details = parts[3];

                    // Creăm o nouă tranzacție (fără timestamp, va fi acum)
                    Transaction transaction = new Transaction(type, amount, details);
                    transactions.add(transaction);
                }
            }

            // Recalculăm balanța
            recalculateBalance();

        } catch (IOException e) {
            System.err.println("Eroare la încărcarea tranzacțiilor: " + e.getMessage());
        }
    }

    private static void saveTransactions() {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_FILE)) {
            for (Transaction transaction : transactions) {
                // Format: tip,sumă,dată,detalii
                writer.write(String.format("%s,%.2f,%s,%s\n",
                        transaction.getType(),
                        transaction.getAmount(),
                        transaction.getFormattedDate(),
                        transaction.getDetails()));
            }
        } catch (IOException e) {
            System.err.println("Eroare la salvarea tranzacțiilor: " + e.getMessage());
        }
    }

    private static void recalculateBalance() {
        balance = 1000.00; // Resetăm la soldul inițial

        for (Transaction transaction : transactions) {
            if (transaction.getType().equals("Depunere")) {
                balance += transaction.getAmount();
            } else if (transaction.getType().equals("Retragere") ||
                    transaction.getType().equals("Trimite")) {
                balance -= transaction.getAmount();
            }
        }
    }
}