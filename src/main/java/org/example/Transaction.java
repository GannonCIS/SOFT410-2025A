package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Transaction {
    void transactionFun(int accNo) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Receiver's Account Number: ");
        int receiverAcc = scanner.nextInt();
        System.out.print("Amount: ");
        int transferAmount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Remarks: ");
        String remarks = scanner.nextLine();
        System.out.println();
        allTransaction(accNo, receiverAcc, transferAmount, remarks);
    }

    void allTransaction(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        if (!rAccCheck(rAccNo)) {
            System.out.println("Incorrect Account Number!");
            return;
        }
        if (!sAccBalCheck(accNo, tAmount)) {
            System.out.println("Insufficient Balance!");
            return;
        }
        applyTransaction(accNo, rAccNo, tAmount);
        writeTransaction(accNo, rAccNo, tAmount, tRemarks);
        System.out.println("Transaction Successful!");
        System.out.println("Press Enter key to continue...");
        new Scanner(System.in).nextLine();
        Main.menu(accNo);
    }

    boolean rAccCheck(int rAccNo) throws IOException {
        try (Scanner scanner = new Scanner(new File("db/balanceDB.txt"))) {
            while (scanner.hasNextLine()) {
                String[] subLine = scanner.nextLine().split(" ");
                if (rAccNo == Integer.parseInt(subLine[0])) return true;
            }
        }
        return false;
    }

    boolean sAccBalCheck(int accNo, int tAmount) throws IOException {
        try (Scanner scanner = new Scanner(new File("db/balanceDB.txt"))) {
            while (scanner.hasNextLine()) {
                String[] subLine = scanner.nextLine().split(" ");
                if (accNo == Integer.parseInt(subLine[0]) && tAmount <= Integer.parseInt(subLine[1])) return true;
            }
        }
        return false;
    }

    void applyTransaction(int accNo, int rAccNo, int tAmount) throws IOException {
        StringBuilder newInfo = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("db/balanceDB.txt"))) {
            while (scanner.hasNextLine()) {
                String[] subLine = scanner.nextLine().split(" ");
                int a = Integer.parseInt(subLine[0]);
                int b = Integer.parseInt(subLine[1]);
                if (accNo == a) b -= tAmount;
                else if (rAccNo == a) b += tAmount;
                newInfo.append(a).append(" ").append(b).append("\n");
            }
        }
        try (Writer writer = new FileWriter("db/balanceDB.txt")) {
            writer.write(newInfo.toString());
        }
    }

    void writeTransaction(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        debitWrite(accNo, rAccNo, tAmount, tRemarks);
        creditWrite(accNo, rAccNo, tAmount, tRemarks);
    }

    void debitWrite(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        String description = "Transfer to " + rAccNo;
        String date = java.time.LocalDate.now().toString();
        String time = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
        try (Writer writer = new FileWriter("db/Bank Statement/acc_" + accNo + ".txt", true)) {
            writer.write(description + " Debit " + tAmount + " " + tRemarks + " " + date + " " + time + "\n");
        }
    }

    void creditWrite(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        String description = "Transfer from " + accNo;
        String date = java.time.LocalDate.now().toString();
        String time = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
        try (Writer writer = new FileWriter("db/Bank Statement/acc_" + rAccNo + ".txt", true)) {
            writer.write(description + " Credit " + tAmount + " " + tRemarks + " " + date + " " + time + "\n");
        }
    }
}
