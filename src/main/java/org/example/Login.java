package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    void loginFun() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        while (!success) {
            System.out.print("Enter your Account Number: ");
            int accNo = scanner.nextInt();
            System.out.print("Enter your Password: ");
            String pass = scanner.next();
            success = loginAuth(accNo, pass);
        }
    }

    boolean loginAuth(int accNo, String pass) throws IOException {
        File file = new File("db/credentials.txt");
        try (Scanner scanner = new Scanner(file)) {
            boolean incPass = false;
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ");
                if (accNo == Integer.parseInt(parts[0]) && pass.equals(parts[1])) {
                    System.out.println("Login Successful!!\n");
                    Main.menu(accNo);
                    return true;
                } else if (accNo == Integer.parseInt(parts[0])) {
                    incPass = true;
                }
            }
            if (incPass) System.out.println("\nIncorrect Password! Try again.\n");
            else System.out.println("\nAccount doesn't exist! Try again.\n");
        }
        return false;
    }
}
