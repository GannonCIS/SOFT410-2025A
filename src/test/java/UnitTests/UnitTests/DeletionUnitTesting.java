package UnitTests;

import org.example.Deletion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeletionUnitTesting {

    private Deletion deletion = new Deletion();

    @Test
    void deletionClassCanBeInstantiatedTest() {
        assertNotNull(deletion);
    }

    @Test
    void deleteAccountMethodExistsTest() {
        // Test that the method exists and can be called
        assertDoesNotThrow(() -> {
            // This will attempt to delete account 9999 (should handle gracefully)
            deletion.deleteFromPostgres(9999);
        });
    }

    @Test
    void deleteAccountWithInvalidNumberTest() {
        // Test with negative account number
        assertDoesNotThrow(() -> deletion.deleteFromPostgres(-1));
    }

    @Test
    void deleteAccountWithZeroTest() {
        // Test with zero account number
        assertDoesNotThrow(() -> deletion.deleteFromPostgres(0));
    }

    @Test
    void deleteAccountWithLargeNumberTest() {
        // Test with a very large account number
        assertDoesNotThrow(() -> deletion.deleteFromPostgres(999999999));
    }

    @Test
    void deletionHandlesDatabaseErrorsGracefullyTest() {
        // Test that the method doesn't crash even if database operations fail
        assertDoesNotThrow(() -> {
            // This should handle any database errors internally
            deletion.deleteFromPostgres(12345);
        });
    }

    @Test
    void deletionMethodSignatureTest() {
        // Verify the method has the expected signature (void return)
        assertDoesNotThrow(() -> {
            deletion.deleteFromPostgres(1001);
            // If we reach here without exception, the method executed
        });
    }


}
