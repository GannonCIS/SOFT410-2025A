package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

//Observer pattern: Subject (the thing being observed)

/*Benefit of observer: The Transaction class no longer needs to
know HOW to write bank statements, it just announces WHEN transactions happen.
The BankStatementObserver handles the "how".
*/
public class Transaction {

    //OBSERVER PATTERN: List to keep track of all observers
    private final List<TransactionObserver> observes = new ArrayList<>();
    private final BalanceRepository balanceRepo = new BalanceRepository();

    //OBSERVER PATTERN: Constructor to register observer
    public Transaction(){
        observes.add(new BankStatementObserver());
    }

    //OBSERVER PATTERN: Method to add more observers
    public void addObserver(TransactionObserver observer){
        observes.add(observer);
    }


    public void notifyObservers(TransactionEvent event){
        for(TransactionObserver observer : observes){
            try{
                observer.onTransactionCompleted(event);
            }catch(Exception e){
                System.out.println("Observer error: " + e.getMessage());
            }
        }
    }




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



    void allTransaction(int accNo, int receiverAcc, int amount, String remarks) throws IOException {

        if (!balanceRepo.accountExists(receiverAcc)) {
            System.out.println("Incorrect Account Number!");
            return;
        }

        if (!hasSufficientBalance(accNo, amount)) {
            System.out.println("Insufficient Balance!");
            return;
        }

        applyTransaction(accNo, receiverAcc, amount);

        notifyObservers(new TransactionEvent(
                accNo,
                receiverAcc,
                amount,
                remarks,
                true
        ));

        System.out.println("Transaction Successful!");
        System.out.println("Press Enter key to continue...");
        new Scanner(System.in).nextLine();
        Main.menu(accNo);
    }

    private boolean hasSufficientBalance(int accNo, int amount) throws IOException {
        int balance = balanceRepo.getBalance(accNo);
        return balance != -1 && balance >= amount;
    }

    void applyTransaction(int accNo, int receiverAcc, int amount) throws IOException {
        balanceRepo.updateBalances(accNo, receiverAcc, amount);
    }
}