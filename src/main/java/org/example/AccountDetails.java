package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AccountDetails {
    public void accountDetailsFun(int accNo) throws IOException {
        File file = new File("db/userDB.txt");
        String wholeDetail = "";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] subLine = scanner.nextLine().split(" ");
                if (accNo == Integer.parseInt(subLine[0])) {
                    wholeDetail = String.join(" ", subLine);
                    break;
                }
            }
        }

        if (wholeDetail.isEmpty()) {
            System.out.println("Account not found!");
            return;
        }

        String[] detail = wholeDetail.split(" ");
        System.out.println("Account Details: ");
        System.out.println("┌────────────────────────────────┐");
        System.out.println("  Full Name: " + detail[1] + " " + detail[2]);
        System.out.println("  Account Number: " + detail[0]);
        System.out.println("  Gender: " + detail[4]);
        System.out.println("  Address: " + detail[5]);
        System.out.println("  Date of Birth: " + detail[3]);
        System.out.println("  Phone number: " + detail[6]);
        System.out.println("  Email: " + detail[7]);
        System.out.println("  Identification: " + detail[8]);
        System.out.println("└────────────────────────────────┘");

        // KEEP THIS MESSAGE but continue automatically
        System.out.println("\nPress Enter key to continue.");

        // Add a small delay to simulate "pressing Enter" then continue automatically
        try {
            Thread.sleep(1000); // Wait 1 second to simulate the Enter key press
        } catch (InterruptedException e) {
            // Ignore interruption
        }
        Main.menu(accNo);
    }
}
