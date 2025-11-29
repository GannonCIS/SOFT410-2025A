package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class Login {

    public void loginFun() throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Acc No: ");
        int accNo = s.nextInt();
        System.out.print("Password: ");
        String pass = s.next();

        String hash = getPasswordHash(accNo);
        if (hash != null && BCrypt.checkpw(pass, hash)) {
            System.out.println("Login successful!");
            Main.menu(accNo);
        } else {
            System.out.println("Invalid credentials!");
            loginFun();
        }
    }

    private String getPasswordHash(int accNo) throws Exception {
        String query = "SELECT password_hash FROM users WHERE username = ?";
        try (var conn = DB.get(); var ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(accNo));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString("password_hash") : null;
            }
        }
    }

    public boolean checkPassword(int accNo, String pass) throws Exception {
        String hash = getPasswordHash(accNo);
        return hash != null && org.mindrot.jbcrypt.BCrypt.checkpw(pass, hash);
    }

}
