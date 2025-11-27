package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observer;


//Concrete observer
public class BankStatementObserver implements TransactionObserver {

    @Override
    public void onTransactionCompleted(TransactionEvent event) {

        //only write statements for successful transactions
        if (!event.isSuccess()) return;

        try {
            debitWrite(event);     // Try to write
            creditWrite(event);    // Try to write
        } catch (IOException e) {
            // If anything fails, show error but DON'T crash
            System.err.println("Failed to write bank statement: " + e.getMessage());
        }
// Transaction continues, user doesn't even know there was an error

    }

    public void debitWrite(TransactionEvent event) throws IOException {
        Files.createDirectories(Paths.get("db/Bank Statement"));

        String description = "Transfer to " + event.getToAccount();
        String date = event.getTimestamp().toLocalDate().toString();
        String time = event.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        try (Writer writer = new FileWriter("db/Bank Statement/acc_" + event.getFromAccount() + ".txt", true)) {
            writer.write(description + " Debit " + event.getAmount() + " " + event.getRemarks() + " " + date + " " + time + "\n");
        }

    }

    public void creditWrite(TransactionEvent event) throws IOException {
        Files.createDirectories(Paths.get("db/Bank Statement"));

        String description = "Transfer from " + event.getFromAccount();
        String date = event.getTimestamp().toLocalDate().toString();
        String time = event.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        try (Writer writer = new FileWriter("db/Bank Statement/acc_" + event.getToAccount() + ".txt", true)) {
            writer.write(description + " Credit " + event.getAmount() + " " + event.getRemarks() + " " + date + " " + time + "\n");
        }
    }


}