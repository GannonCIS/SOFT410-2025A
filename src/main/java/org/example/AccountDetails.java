package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AccountDetails {

    public void accountDetailsFun(int accNo) throws Exception {
        String[] data = getUser(accNo);
        if (data == null) { System.out.println("Account not found!"); return; }

        System.out.printf("""
                Full Name: %s %s
                DOB: %s
                Gender: %s
                Address: %s
                Phone: %s
                Email: %s
                ID: %s
                """, data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);

        new Scanner(System.in).nextLine();
        Main.menu(accNo);
    }

    private String[] getUser(int accNo) throws Exception {
        String query = "SELECT first_name, last_name, dob, gender, address, phone, email, id_number FROM user_details WHERE acc_no=?";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, accNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new String[]{
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("id_number")
                };
            }
        }
    }
}
