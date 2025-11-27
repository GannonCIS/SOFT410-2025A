package test;

import org.example.BankStatementObserver;
import org.example.Transaction;
import org.example.TransactionEvent;
import org.example.TransactionObserver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionUnitTest {

    //Test if it can add observers
    @Test
    void AddObserversTest(){
        Transaction transaction = new Transaction();
        TransactionObserver observer = new BankStatementObserver();

        transaction.addObserver(observer);

        //If no exception, it works
        assertTrue(true);
    }

    @Test
    void notifyObserversTest(){
        Transaction transaction = new Transaction();
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "test", true);

        //Should not throw exception when notifying
        assertDoesNotThrow(() -> transaction.notifyObservers(event));

    }



}
