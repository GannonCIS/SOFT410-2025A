package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BankStatementObserver implements TransactionObserver {

    @Override
    public void onTransactionCompleted(TransactionEvent e) {
        if (!e.isSuccess()) return;

        String query = "INSERT INTO transactions(acc_no, amount, description, remarks, transaction_time) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, e.getFromAccount());
            ps.setInt(2, e.getAmount());
            ps.setString(3, "Transfer");
            ps.setString(4, e.getRemarks());
            ps.setObject(5, e.getTimestamp());
            ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Failed to log transaction: " + ex.getMessage());
        }
    }

    public void logDeposit(TransactionEvent e) {
        String query = "INSERT INTO transactions(acc_no, amount, description, remarks, transaction_time) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, e.getFromAccount());
            ps.setInt(2, e.getAmount());
            ps.setString(3, "Deposit");
            ps.setString(4, "Cash deposit");
            ps.setObject(5, e.getTimestamp());
            ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Failed to log deposit: " + ex.getMessage());
        }
    }
}
