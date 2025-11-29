package org.example;

public interface TransactionObserver {
    void onTransactionCompleted(TransactionEvent event);
}
