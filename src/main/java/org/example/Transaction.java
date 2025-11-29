package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Transaction {

    private final BankStatementObserver logger = new BankStatementObserver();

    public void transactionFun(int accNo) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Receiver Acc: ");
        int r = s.nextInt();
        System.out.print("Amount: ");
        int amt = s.nextInt();
        System.out.print("Remarks: ");
        String remark = s.next();

        if (!accountExists(r)) {
            System.out.println("Invalid receiver!");
            return;
        }

        int fromBal = getBalance(accNo);
        if (fromBal < amt) {
            System.out.println("Insufficient balance!");
            return;
        }

        updateBalance(accNo, fromBal - amt);
        updateBalance(r, getBalance(r) + amt);

        TransactionEvent event = new TransactionEvent(accNo, r, amt, remark, true);
        logger.onTransactionCompleted(event);

        System.out.println("Transaction successful!");
        Main.menu(accNo);
    }

    private boolean accountExists(int accNo) throws Exception {
        String query = "SELECT 1 FROM users WHERE username = ?";
        try (var conn = DB.get(); var ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(accNo));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private int getBalance(int accNo) throws Exception {
        String query = "SELECT balance FROM users WHERE username = ?";
        try (var conn = DB.get(); var ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(accNo));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("balance") : 0;
            }
        }
    }

    private void updateBalance(int accNo, int newBalance) throws Exception {
        String query = "UPDATE users SET balance = ? WHERE username = ?";
        try (var conn = DB.get(); var ps = conn.prepareStatement(query)) {
            ps.setInt(1, newBalance);
            ps.setString(2, String.valueOf(accNo));
            ps.executeUpdate();
        }
    }

    public boolean accountExistsForTest(int accNo) throws Exception {
        return accountExists(accNo);
    }

    public int getBalanceForTest(int accNo) throws Exception {
        return getBalance(accNo);
    }
}
