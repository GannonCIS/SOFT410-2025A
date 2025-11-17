package org.example;

import java.time.LocalDateTime;

//Event object (Data package with event information)

//Instead of passing 5 separate parameters, we pass one clean object
public class TransactionEvent {
    private final int fromAccount;
    private final int toAccount;
    private final int amount;
    private final String remarks;
    private final LocalDateTime timestamp;
    private final boolean success;


    public TransactionEvent(int fromAccount, int toAccount, int amount, String remarks, boolean success) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.remarks = remarks;
        this.timestamp = LocalDateTime.now();
        this.success = success;
    }



    //Getters
    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isSuccess() {
        return success;
    }


}
