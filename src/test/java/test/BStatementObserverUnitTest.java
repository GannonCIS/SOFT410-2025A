package test;

import org.example.BankStatementObserver;
import org.example.TransactionEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BStatementObserverUnitTest {

    @Test
    void onTransactionCompleted_Success() {
        BankStatementObserver observer = new BankStatementObserver();
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);
        assertDoesNotThrow(() -> observer.onTransactionCompleted(event));
    }

    @Test
    void onTransactionCompleted_Failure() {
        BankStatementObserver observer = new BankStatementObserver();
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", false);
        assertDoesNotThrow(() -> observer.onTransactionCompleted(event));
    }
}
