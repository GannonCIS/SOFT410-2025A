package test;

import org.example.TransactionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionEventUnitTest {

    private TransactionEvent event;

    @BeforeEach
    void setup() {
        event = new TransactionEvent(1001, 1002, 500, "Test", true);
    }

    @Test
    void getFromAccount() {
        assertEquals(1001, event.getFromAccount());
    }

    @Test
    void getToAccount() {
        assertEquals(1002, event.getToAccount());
    }

    @Test
    void getAmount() {
        assertEquals(500, event.getAmount());
    }

    @Test
    void getRemarks() {
        assertEquals("Test", event.getRemarks());
    }

    @Test
    void getTimestamp() {
        assertNotNull(event.getTimestamp());
    }

    @Test
    void isSuccess() {
        assertTrue(event.isSuccess());
    }
}
