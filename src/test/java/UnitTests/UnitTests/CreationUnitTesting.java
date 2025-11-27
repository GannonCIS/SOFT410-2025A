package UnitTests;

import org.example.Creation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreationUnitTesting {

    private Creation creation = new Creation();

    @Test
    void accNoCreationReturnsValidNumberTest() {
        assertDoesNotThrow(() -> {
            int result = creation.accNoCreation();
            // Should return at least 1 (default) or higher
            assertTrue(result >= 1);
        });
    }

    @Test
    void writeToPostgresHandlesInputTest() {
        // Test that the method doesn't crash with valid input
        int testAccNo = 9999;
        String[] testData = {
                "Test", "User", "2000-01-01", "Other",
                "Test Address", "555-0000", "test@test.com",
                "TEST123", "testpass"
        };

        assertDoesNotThrow(() -> creation.writeToPostgres(testAccNo, testData));
    }

    @Test
    void createAccFunRunsWithoutCrashingTest() {
        // Test that the main creation method starts without crashing
        assertDoesNotThrow(() -> creation.createAccFun());
    }

    @Test
    void getUserInfoFromUserMethodExistsTest() {
        // Test that the method exists and returns correct array structure
        assertDoesNotThrow(() -> {
            // Note: This will actually wait for user input during testing
            // You might want to skip this test or refactor your code
            String[] result = creation.getUserInfoFromUser();
            assertEquals(9, result.length);
        });
    }

    @Test
    void classInstanceCanBeCreatedTest() {
        // Basic sanity test
        assertNotNull(creation);
    }

    @Test
    void initialBalanceConstantTest() {
        // Test that uses reflection to check the constant value
        assertDoesNotThrow(() -> {
            java.lang.reflect.Field field = Creation.class.getDeclaredField("INITIAL_BAL");
            field.setAccessible(true);
            int initialBal = (int) field.get(creation);
            assertEquals(69, initialBal);
        });
    }



}
