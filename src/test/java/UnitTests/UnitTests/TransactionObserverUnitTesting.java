package UnitTests;

import org.example.Transaction;
import org.example.TransactionEvent;
import org.example.TransactionObserver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TransactionObserverUnitTesting {

    @Test
    void observerTest(){
        TransactionObserver observer = new TransactionObserver() {
            @Override
            public void onTransactionCompleted(TransactionEvent event) {}
        };
        assertNotNull(observer);
    }

}
