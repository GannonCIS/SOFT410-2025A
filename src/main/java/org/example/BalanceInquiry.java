package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BalanceInquiry {
    void balanceInquiryFun(int accNo) throws IOException {
        File file = new File("db/balanceDB.txt");
        int accBalance = -1;


        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] subLine = scanner.nextLine().split(" ");
                if (accNo == getAccountNumber(subLine)) {
                    accBalance = getBalance(subLine);
                    break;
                }
            }
        }

        if (accBalance == -1) {
            System.out.println("We're having some issues, Try Again!");
            return;
        }

        System.out.println("┌───────────────────────────────┐");
        System.out.println("  Your current balance is $" + accBalance + "   ");
        System.out.println("└───────────────────────────────┘");
        System.out.println("Press Enter key to continue...");
        new Scanner(System.in).nextLine();
        Main.menu(accNo);
    }

    private int getAccountNumber(String[] subLine) { return Integer.parseInt(subLine[0]); }
    private int getBalance(String[] subLine) { return Integer.parseInt(subLine[1]); }
}
