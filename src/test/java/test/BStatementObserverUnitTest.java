package test;

import org.example.BankStatementObserver;
import org.example.TransactionEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class BStatementObserverUnitTest {

    @Test
    void onTransactionCompletedTest(){
        BankStatementObserver observer = new BankStatementObserver();
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);

        assertDoesNotThrow(() -> observer.onTransactionCompleted(event));

    }


    @Test
    void debitWriteTest(){
        BankStatementObserver observer = new BankStatementObserver();
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);

        assertDoesNotThrow(() -> observer.debitWrite(event));

    }

    @Test
    void creditWriteTest(){
        BankStatementObserver observer = new BankStatementObserver();
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);

        assertDoesNotThrow(() -> observer.creditWrite(event));

    }


}
