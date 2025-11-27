package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class Creation {

    private static final int INITIAL_BAL = 69;

    void createAccFun() {
        try {
            int accNo = accNoCreation();
            String[] accLineInfo = getUserInfoFromUser();
            writeToPostgres(accNo, accLineInfo);

            System.out.println("\nAccount created successfully!\n");
            System.out.println("Your account number is: " + accNo);
            System.out.println("Your password is: " + accLineInfo[8] + "\n");

            Main.menu(accNo);
        } catch (Exception e) {
            System.out.println("Account creation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    int accNoCreation() {
        int accNo = 1;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/auth_system",
                "postgres", "2331");
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT MAX(username::int) AS max_acc FROM users");
             var rs = ps.executeQuery()) {

            if (rs.next() && rs.getInt("max_acc") != 0) {
                accNo = rs.getInt("max_acc") + 1;
            }

        } catch (Exception e) {
            System.out.println("Error generating account number: " + e.getMessage());
        }
        return accNo;
    }

    void writeToPostgres(int accNo, String[] acc) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/auth_system",
                "postgres", "2331")) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users(username, password_hash, role) VALUES (?, ?, ?)")) {
                ps.setString(1, String.valueOf(accNo));
                ps.setString(2, BCrypt.hashpw(acc[8], BCrypt.gensalt()));
                ps.setString(3, "user");
                ps.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(
                    "INSERT INTO user_details(acc_no, first_name, last_name, dob, gender, address, phone, email, citizenship, balance) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                ps2.setInt(1, accNo);
                for (int i = 0; i < 8; i++) ps2.setString(i + 2, acc[i]);
                ps2.setDate(4, Date.valueOf(acc[2])); // dob conversion
                ps2.setInt(10, INITIAL_BAL);
                ps2.executeUpdate();
            }

            System.out.println("\nPostgreSQL insertion successful!");

        } catch (Exception e) {
            System.out.println("PostgreSQL write failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    String[] getUserInfoFromUser() {
        String[] accLineInfo = new String[9];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name (First Last): ");
        String[] fullNameArr = scanner.nextLine().trim().split(" ");
        if (fullNameArr.length != 2) {
            System.out.println("Please provide both first name and last name.");
            return getUserInfoFromUser();
        }
        accLineInfo[0] = fullNameArr[0];
        accLineInfo[1] = fullNameArr[1];

        System.out.println("Enter your Date of Birth (YYYY-MM-DD): ");
        accLineInfo[2] = scanner.nextLine();
        System.out.println("Enter your Gender: ");
        accLineInfo[3] = scanner.nextLine();
        System.out.println("Enter your Address: ");
        accLineInfo[4] = scanner.nextLine();
        System.out.println("Enter your Phone Number: ");
        accLineInfo[5] = scanner.nextLine();
        System.out.println("Enter your Email: ");
        accLineInfo[6] = scanner.nextLine();
        System.out.println("Enter your Citizenship Number: ");
        accLineInfo[7] = scanner.nextLine();
        System.out.println("Create a Password for your Account: ");
        accLineInfo[8] = scanner.nextLine();

        return accLineInfo;
    }
}