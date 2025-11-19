package org.example;

import java.io.IOException;
import java.util.Scanner;


//Client class (Factory pattern)
public class Main {

    private static BankServiceFactory serviceFactory;

    public static void main(String[] args) throws IOException {
        serviceFactory = new BankServiceFactory();
        intro();
    }

    private static void intro() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("┌───────────────────────────────┐");
        System.out.println("│ Welcome to SawOnGam Bank Ltd. │");
        System.out.println("├───────────────────────────────┤");
        System.out.println("│ Type 1: Login                 │");
        System.out.println("│ Type 2: Create Account        │");
        System.out.println("└───────────────────────────────┘");
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
        serviceFactory.createLogin().loginFun();
    }

    private static void createAcc() throws IOException {
        serviceFactory.createCreation().createAccFun();
    }

    static void menu(int accNo) throws IOException {
        printMenu();
        int choice = new Scanner(System.in).nextInt();
        handleMenuChoice(choice, accNo);
    }

    private static void printMenu() {
        System.out.println("┌────────────────────────────┐");
        System.out.println("│           Menu:            │");
        System.out.println("│ Type 1: Balance Inquiry    │");
        System.out.println("│ Type 2: Account Details    │");
        System.out.println("│ Type 3: Fund Transfer      │");
        System.out.println("│ Type 4: Bank Statement     │");
        System.out.println("│ Type 5: Account Closure    │");
        System.out.println("│ Type 6: Log out            │");
        System.out.println("│ Type 7: Exit               │");
        System.out.println("└────────────────────────────┘");
    }

    private static void handleMenuChoice(int choice, int accNo) throws IOException {
        switch (choice) {
            case 1 -> serviceFactory.createBalanceInquiry().balanceInquiryFun(accNo);
            case 2 -> serviceFactory.createAccountDetails().accountDetailsFun(accNo);
            case 3 -> serviceFactory.createTransaction().transactionFun(accNo);
            case 4 -> serviceFactory.createBankStatement().bankStatementFun(accNo);
            case 5 -> {
                Deletion d = serviceFactory.createDeletion();
                d.accCloseFun(accNo, "db/credentials.txt");
                d.delLine(accNo, "db/userDB.txt");
                d.delLine(accNo, "db/balanceDB.txt");
                System.out.println("\nAccount successfully Deleted.");
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
