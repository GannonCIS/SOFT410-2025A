package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BalanceRepository {

    public int getBalance(int accNo) {
        String query = "SELECT balance FROM users WHERE username = ?";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(accNo));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("balance") : -1;
            }
        } catch (Exception e) { return -1; }
    }

    public void updateBalance(int accNo, int newBalance) throws Exception {
        String query = "UPDATE users SET balance = ? WHERE username = ?";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, newBalance);
            ps.setString(2, String.valueOf(accNo));
            ps.executeUpdate();
        }
    }

    public void transfer(int fromAcc, int toAcc, int amount) throws Exception {
        int fromBal = getBalance(fromAcc);
        int toBal = getBalance(toAcc);
        updateBalance(fromAcc, fromBal - amount);
        updateBalance(toAcc, toBal + amount);
    }
}
