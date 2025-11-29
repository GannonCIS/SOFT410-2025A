package org.example;

import java.util.Scanner;

public class Deletion {

    public void accCloseFun(int accNo) throws Exception {
        System.out.println("Delete account? 1: Yes 2: No");
        int choice = new Scanner(System.in).nextInt();
        if (choice != 1) {
            Main.menu(accNo);
            return;
        }

        deleteUser(accNo);
        deleteBalance(accNo);

        System.out.println("Account deleted.");
        Main.menu(accNo);
    }

    private void deleteUser(int accNo) throws Exception {
        String query = "DELETE FROM user_details WHERE acc_no=?";
        try (var conn = DB.get(); var ps = conn.prepareStatement(query)) {
            ps.setInt(1, accNo);
            ps.executeUpdate();
        }
    }

    private void deleteBalance(int accNo) throws Exception {
        String query = "DELETE FROM users WHERE username=?";
        try (var conn = DB.get(); var ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(accNo));
            ps.executeUpdate();
        }
    }
}
