package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BankStatement {
    void bankStatementFun(int accNo) throws IOException {
        File file = new File("db/Bank Statement/acc_" + accNo + ".txt");
//
        try (Scanner scanner = new Scanner(file)) {
            printHeader();
            while (scanner.hasNextLine()) {
                String[] trLine = scanner.nextLine().split(" ");
                if (trLine.length >= 8) {
                    String description = trLine[0] + " " + trLine[1] + " " + trLine[2];
                    System.out.printf("%-21s | %-6s | $%-6s | %-7s | %-10s | %-8s%n",
                            description, trLine[3], trLine[4], trLine[5], trLine[6], trLine[7]);
                }
            }
            printFooter();
        } catch (FileNotFoundException e) {
            System.out.println("No Transaction found!");
        }

        System.out.println("\nPress Enter key to continue...");
        new Scanner(System.in).nextLine();
        Main.menu(accNo);
    }
//
    private void printHeader() {
        System.out.println("\n");
        System.out.println("                           | Bank Statement |");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("%-21s | %-6s | %-6s | %-7s | %-10s | %-8s%n",
                "Description", "Type", "Amount", "Remarks", "Date", "Time");
        System.out.println("---------------------------------------------------------------------------");
    }
    private void printFooter() {
        System.out.println("---------------------------------------------------------------------------");
    }
}
