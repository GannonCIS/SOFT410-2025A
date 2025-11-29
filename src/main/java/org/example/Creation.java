package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class Creation {

    public void createAccFun() {
        try (Scanner s = new Scanner(System.in)) {
            int accNo = generateAccNo();

            System.out.print("First Name: "); String firstName = s.next();
            System.out.print("Last Name: "); String lastName = s.next();
            System.out.print("DOB (yyyy-MM-dd): "); Date dob = Date.valueOf(s.next());
            System.out.print("Gender: "); String gender = s.next();
            System.out.print("Address: "); String address = s.next();
            System.out.print("Phone: "); String phone = s.next();
            System.out.print("Email: "); String email = s.next();
            System.out.print("ID Number: "); String idNumber = s.next();
            System.out.print("Password: "); String password = s.next();

            insertUser(accNo, password);
            insertUserDetails(accNo, firstName, lastName, dob, gender, address, phone, email, idNumber);

            System.out.printf("Account created! Number: %d, Password: %s%n", accNo, password);
            Main.menu(accNo);

        } catch (Exception e) {
            System.out.println("Account creation failed: " + e.getMessage());
        }
    }

    private int generateAccNo() throws Exception {
        String query = "SELECT MAX(username::int) AS max_acc FROM users";
        try (Connection conn = DB.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(query)) {
            return (rs.next() && rs.getInt("max_acc") != 0) ? rs.getInt("max_acc") + 1 : 1;
        }
    }

    private void insertUser(int accNo, String password) throws Exception {
        String query = "INSERT INTO users(username, password_hash, balance) VALUES (?, ?, 0)";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(accNo));
            ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            ps.executeUpdate();
        }
    }

    private void insertUserDetails(int accNo, String firstName, String lastName, Date dob,
                                   String gender, String address, String phone,
                                   String email, String idNumber) throws Exception {
        String query = "INSERT INTO user_details(acc_no, first_name, last_name, dob, gender, address, phone, email, id_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, accNo);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setDate(4, dob);
            ps.setString(5, gender);
            ps.setString(6, address);
            ps.setString(7, phone);
            ps.setString(8, email);
            ps.setString(9, idNumber);
            ps.executeUpdate();
        }
    }
}
