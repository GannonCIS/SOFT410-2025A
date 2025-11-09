package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Deletion {

    void accCloseFun(int accNo, String fileName) throws IOException {
        System.out.println("Are you sure want to delete your account?");
        System.out.println("Type 1: Yes");
        System.out.println("Type 2: No");
        Scanner tscanner = new Scanner(System.in);
        int conf = tscanner.nextInt();
        if (conf ==2) {
            Main.menu(accNo);
            return;
        } else if (conf != 1) {
            System.out.println("Incorrect! Choose a valid option again.\n");
            accCloseFun(accNo, fileName);
            return;
        }
        delLine(accNo, fileName);
    }

    void delLine(int accNo, String fileName) throws IOException {
        File file = new File(fileName);
        StringBuilder newInfo = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] subLine = scanner.nextLine().split(" ");
                if (accNo == Integer.parseInt(subLine[0])) continue;
                newInfo.append(String.join(" ", subLine)).append("\n");
            }
        }

        if (newInfo.length() > 0 && newInfo.charAt(newInfo.length() - 1) == '\n') {
            newInfo.deleteCharAt(newInfo.length() - 1);
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(newInfo.toString());
        }
    }
}
