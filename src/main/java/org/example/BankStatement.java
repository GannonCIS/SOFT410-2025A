package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BankStatement {
    public void printStatement(int accNo) {
        String query = "SELECT amount, description, remarks, transaction_time FROM transactions WHERE account = ? ORDER BY transaction_time";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, accNo);
            try (ResultSet rs = ps.executeQuery()) {

                System.out.println("+--------+------------------+----------------+-------------------------+");
                System.out.printf("| %-6s | %-16s | %-14s | %-23s |%n", "Amount", "Description", "Remarks", "Time");
                System.out.println("+--------+------------------+----------------+-------------------------+");

                while (rs.next()) {
                    System.out.printf("| %6d | %-16s | %-14s | %-23s |%n",
                            rs.getInt("amount"),
                            rs.getString("description"),
                            rs.getString("remarks"),
                            rs.getTimestamp("transaction_time").toString());
                }

                System.out.println("+--------+------------------+----------------+-------------------------+");
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch statement: " + e.getMessage());
        }

        new Scanner(System.in).nextLine();
        try { Main.menu(accNo); } catch (Exception ignored) {}
    }
}
