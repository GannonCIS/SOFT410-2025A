package org.example;

import java.util.Scanner;

public class Deposit {
    private final BalanceRepository repo = new BalanceRepository();

    public void depositFun(int accNo) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Amount to deposit: "); int amt = s.nextInt();
        if (amt <= 0) { System.out.println("Must be positive."); Main.menu(accNo); return; }

        int current = repo.getBalance(accNo);
        repo.updateBalance(accNo, current + amt);
        System.out.println("Deposited: $" + amt);
        Main.menu(accNo);
    }
}
