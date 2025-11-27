package org.example;

//Observer interface: contract for observers
public interface TransactionObserver {

    //Any class that wants to be notified when a transaction happens must implement this method
    void onTransactionCompleted(TransactionEvent event);



}
