package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception { intro(); }

    private static void intro() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("+-------------------------------+");
        System.out.println("| Welcome to SawOnGam Bank Ltd. |");
        System.out.println("+-------------------------------+");
        System.out.println("| 1: Login                      |");
        System.out.println("| 2: Create Account             |");
        System.out.println("+-------------------------------+");

        int c = sc.nextInt();
        switch (c) {
            case 1 -> loginAcc();
            case 2 -> createAcc();
            default -> { System.out.println("Invalid option!"); intro(); }
        }
    }

    private static void loginAcc() throws Exception {
        new Login().loginFun();
    }

    private static void createAcc() throws IOException {
        new Creation().createAccFun();
    }

    public static void menu(int accNo) throws Exception {
        printMenu();
        handleMenuChoice(new Scanner(System.in).nextInt(), accNo);
    }

    private static void printMenu() {
        System.out.println("+----------------------------+");
        System.out.println("|            Menu            |");
        System.out.println("| 1: Balance Inquiry         |");
        System.out.println("| 2: Account Details         |");
        System.out.println("| 3: Deposit Money           |");
        System.out.println("| 4: Fund Transfer           |");
        System.out.println("| 5: Bank Statement          |");
        System.out.println("| 6: Account Closure         |");
        System.out.println("| 7: Log out                 |");
        System.out.println("| 8: Exit                    |");
        System.out.println("+----------------------------+");
    }

    private static void handleMenuChoice(int c, int accNo) throws Exception {
        switch (c) {
            case 1 -> new BalanceInquiry().balanceInquiryFun(accNo);
            case 2 -> new AccountDetails().accountDetailsFun(accNo);
            case 3 -> new Deposit().depositFun(accNo);
            case 4 -> new Transaction().transactionFun(accNo);
            case 5 -> new BankStatement().printStatement(accNo);
            case 6 -> new Deletion().accCloseFun(accNo);
            case 7 -> { System.out.println("Logged out."); intro(); }
            case 8 -> System.exit(0);
            default -> { System.out.println("Invalid option."); menu(accNo); }
        }
    }
}
