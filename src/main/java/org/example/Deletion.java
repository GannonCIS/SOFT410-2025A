package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Deletion {

    void accCloseFun(int accNo) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Are you sure you want to delete your account?");
        System.out.println("Type 1: Yes");
        System.out.println("Type 2: No");

        int conf = scanner.nextInt();

        if (conf == 2) {
            Main.menu(accNo);
            return;
        } else if (conf != 1) {
            System.out.println("Incorrect! Choose a valid option again.\n");
            accCloseFun(accNo);
            return;
        }

        deleteFromPostgres(accNo);
        System.out.println("Account deleted successfully!");
    }

    public void deleteFromPostgres(int accNo) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/auth_system",
                "postgres",
                "2331")) {

            try (PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM users WHERE username = ?")) {
                ps.setString(1, String.valueOf(accNo));
                ps.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(
                    "DELETE FROM user_details WHERE acc_no = ?")) {
                ps2.setInt(1, accNo);
                ps2.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("PostgreSQL deletion error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}