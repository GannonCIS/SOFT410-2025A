package test;

import org.example.TransactionEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TransactionEventUnitTest {
    @Test
    void getFromAccountTest() {
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);
        assertEquals(1001, event.getFromAccount());
    }

    @Test
    void getToAccountTest() {
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);
        assertEquals(1002, event.getToAccount());
    }

    @Test
    void getAmountTest() {
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);
        assertEquals(500, event.getAmount());
    }

    @Test
    void getRemarksTest() {
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);
        assertEquals("Test", event.getRemarks());
    }

    @Test
    void getTimestampTest() {
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);
        assertNotNull(event.getTimestamp());
    }

    @Test
    void isSuccessTest() {
        TransactionEvent event = new TransactionEvent(1001, 1002, 500, "Test", true);
        assertTrue(event.isSuccess());
    }




}
