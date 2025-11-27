package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class Login {

    private Connection conn;

    public Login() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/auth_system",
                    "postgres",
                    "2331"
            );
        } catch (SQLException e) {
            System.out.println("Failed to connect to PostgreSQL: " + e.getMessage());
            System.exit(1);
        }
    }

    void loginFun() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        while (!success) {
            System.out.print("Enter your Account Number: ");
            int accNo = scanner.nextInt();
            System.out.print("Enter your Password: ");
            String pass = scanner.next();
            success = loginAuth(accNo, pass);
        }
    }

    boolean loginAuth(int accNo, String pass) throws IOException {
        if (checkPostgres(accNo, pass)) {
            System.out.println("Login Successful!!\n");
            Main.menu(accNo);
            return true;
        } else {
            System.out.println("\nInvalid Account Number or Password! Try again.\n");
            return false;
        }
    }

    boolean checkPostgres(int accNo, String pass) {
        String query = "SELECT password_hash FROM users WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(accNo));
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return false;
                String hash = rs.getString("password_hash");
                return BCrypt.checkpw(pass, hash);
            }
        } catch (SQLException e) {
            System.out.println("PostgreSQL login error: " + e.getMessage());
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing PostgreSQL connection: " + e.getMessage());
        }
    }
}
