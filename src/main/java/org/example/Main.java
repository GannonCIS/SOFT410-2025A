package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        intro();
    }

    private static void intro() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+-------------------------------+");
        System.out.println("| Welcome to SawOnGam Bank Ltd. |");
        System.out.println("+-------------------------------+");
        System.out.println("| Type 1: Login                 |");
        System.out.println("| Type 2: Create Account        |");
        System.out.println("+-------------------------------+");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> loginAcc();
            case 2 -> createAcc();
            default -> {
                System.out.println("Incorrect! Choose a valid option again.");
                intro();
            }
        }
    }

    private static void loginAcc() throws IOException {
        Login login = new Login();
        login.loginFun();
        login.closeConnection();
    }

    private static void createAcc() throws IOException {
        Creation creation = new Creation();
        creation.createAccFun();
    }

    public static void menu(int accNo) throws IOException {
        printMenu();
        int choice = new Scanner(System.in).nextInt();
        handleMenuChoice(choice, accNo);
    }

    private static void printMenu() {
        System.out.println("+----------------------------+");
        System.out.println("|           Menu:            |");
        System.out.println("| Type 1: Balance Inquiry    |");
        System.out.println("| Type 2: Account Details    |");
        System.out.println("| Type 3: Fund Transfer      |");
        System.out.println("| Type 4: Bank Statement     |");
        System.out.println("| Type 5: Account Closure    |");
        System.out.println("| Type 6: Log out            |");
        System.out.println("| Type 7: Exit               |");
        System.out.println("+----------------------------+");
    }

    private static void handleMenuChoice(int choice, int accNo) throws IOException {
        switch (choice) {
            case 1 -> new BalanceInquiry().balanceInquiryFun(accNo);
            case 2 -> new AccountDetails().accountDetailsFun(accNo);
            case 3 -> new Transaction().transactionFun(accNo);
            case 4 -> new BankStatement().bankStatementFun(accNo);
            case 5 -> {
                Deletion deletion = new Deletion();
                deletion.accCloseFun(accNo);
                System.out.println("\nAccount successfully deleted.");
                System.exit(0);
            }
            case 6 -> {
                System.out.println("Logged out successfully!");
                intro();
            }
            case 7 -> System.exit(0);
            default -> {
                System.out.println("Incorrect! Choose a valid option again.\n");
                menu(accNo);
            }
        }
    }
}
