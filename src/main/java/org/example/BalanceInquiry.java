package org.example;

import java.io.IOException;

public class BalanceInquiry {

    private final BalanceRepository repo = new BalanceRepository();

    public void balanceInquiryFun(int accNo) throws Exception {
        int balance = repo.getBalance(accNo);
        if (balance == -1) {
            System.out.println("Account not found!");
            return;
        }
        System.out.println("Your balance: $" + balance);
        new java.util.Scanner(System.in).nextLine();
        Main.menu(accNo);
    }
}
