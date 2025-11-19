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



    void allTransaction(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {

        //Step 1: Validate receiver account
        if (!rAccCheck(rAccNo)) {
            System.out.println("Incorrect Account Number!");
            return;
        }

        //Step 2: Validate sender balance
        if (!sAccBalCheck(accNo, tAmount)) {
            System.out.println("Insufficient Balance!");
            return;
        }

        //Step 3: Apply the transaction
        applyTransaction(accNo, rAccNo, tAmount);

        //Step 4 (OBSERVER PATTERN): Notify observers of successful transaction
        notifyObservers(new TransactionEvent(accNo, rAccNo, tAmount, tRemarks, true));

        //Step 5: Finalize
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

}
