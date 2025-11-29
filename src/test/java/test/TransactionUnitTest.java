package test;

import org.example.BankStatementObserver;
import org.example.Transaction;
import org.example.TransactionEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TransactionUnitTest {

    @Test
    void observerLogging() {
        Transaction t = new Transaction();
        TransactionEvent e = new TransactionEvent(1001, 1002, 500, "test", true);

        assertDoesNotThrow(() -> new BankStatementObserver().onTransactionCompleted(e));
    }
}
