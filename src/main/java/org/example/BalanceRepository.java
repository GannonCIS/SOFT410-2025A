package org.example;

import java.io.*;
import java.util.Scanner;

public class BalanceRepository {

    private static final String BALANCE_FILE = "db/balanceDB.txt";

    public boolean accountExists(int accNo) throws IOException {
        try (Scanner scanner = new Scanner(new File(BALANCE_FILE))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                if (accNo == Integer.parseInt(line[0])) return true;
            }
        }
        return false;
    }

    public int getBalance(int accNo) throws IOException {
        try (Scanner scanner = new Scanner(new File(BALANCE_FILE))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                if (accNo == Integer.parseInt(line[0])) return Integer.parseInt(line[1]);
            }
        }
        return -1;
    }

    public void updateBalances(int sender, int receiver, int amount) throws IOException {
        StringBuilder updated = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(BALANCE_FILE))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                int acc = Integer.parseInt(line[0]);
                int bal = Integer.parseInt(line[1]);

                if (acc == sender) bal -= amount;
                else if (acc == receiver) bal += amount;

                updated.append(acc).append(" ").append(bal).append("\n");
            }
        }
        try (FileWriter writer = new FileWriter(BALANCE_FILE)) {
            writer.write(updated.toString());
        }
    }
}
