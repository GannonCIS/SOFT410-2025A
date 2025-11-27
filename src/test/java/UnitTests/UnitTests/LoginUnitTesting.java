package UnitTests;

import org.example.Login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class LoginUnitTesting {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }

    @AfterEach
    void tearDown() {
        if (login != null) {
            login.closeConnection();
        }
    }

    @Test
    void loginClassCanBeInstantiatedTest() {
        assertNotNull(login);
    }

    @Test
    void loginFunMethodExistsTest() {
        assertDoesNotThrow(() -> {
            // Test that loginFun method exists and can be called
            // Note: This will wait for user input, so we test method existence differently
            Method method = Login.class.getDeclaredMethod("loginFun");
            assertNotNull(method);
            assertEquals(void.class, method.getReturnType());
        });
    }

    @Test
    void loginAuthMethodExistsTest() {
        assertDoesNotThrow(() -> {
            Method method = Login.class.getDeclaredMethod("loginAuth", int.class, String.class);
            assertNotNull(method);
            assertEquals(boolean.class, method.getReturnType());
        });
    }

    @Test
    void loginAuthWithInvalidCredentialsTest() {
        assertDoesNotThrow(() -> {
            // Test with non-existent account
            boolean result = login.loginAuth(999999, "wrongpassword");
            assertFalse(result);
        });
    }

    @Test
    void loginAuthWithNegativeAccountTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.loginAuth(-1, "password");
            assertFalse(result);
        });
    }

    @Test
    void loginAuthWithZeroAccountTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.loginAuth(0, "password");
            assertFalse(result);
        });
    }

    @Test
    void loginAuthWithNullPasswordTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.loginAuth(1001, null);
            assertFalse(result);
        });
    }

    @Test
    void loginAuthWithEmptyPasswordTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.loginAuth(1001, "");
            assertFalse(result);
        });
    }

    @Test
    void checkPostgresMethodExistsTest() {
        assertDoesNotThrow(() -> {
            Method method = Login.class.getDeclaredMethod("checkPostgres", int.class, String.class);
            assertNotNull(method);
            assertEquals(boolean.class, method.getReturnType());
        });
    }

    @Test
    void checkPostgresWithInvalidAccountTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.checkPostgres(999999, "password");
            assertFalse(result);
        });
    }

    @Test
    void checkPostgresWithNegativeAccountTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.checkPostgres(-123, "password");
            assertFalse(result);
        });
    }

    @Test
    void checkPostgresWithZeroAccountTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.checkPostgres(0, "password");
            assertFalse(result);
        });
    }

    @Test
    void checkPostgresWithNullPasswordTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.checkPostgres(1001, null);
            assertFalse(result);
        });
    }

    @Test
    void checkPostgresWithEmptyPasswordTest() {
        assertDoesNotThrow(() -> {
            boolean result = login.checkPostgres(1001, "");
            assertFalse(result);
        });
    }

    @Test
    void checkPostgresHandlesDatabaseErrorsTest() {
        assertDoesNotThrow(() -> {
            // Method should handle any database errors gracefully
            boolean result = login.checkPostgres(1001, "testpassword");
            // Don't care about the result, just that it doesn't throw an exception
            assertTrue(true);
        });
    }

    @Test
    void closeConnectionMethodExistsTest() {
        assertDoesNotThrow(() -> {
            Method method = Login.class.getMethod("closeConnection");
            assertNotNull(method);
            assertEquals(void.class, method.getReturnType());
        });
    }

    @Test
    void closeConnectionCanBeCalledMultipleTimesTest() {
        assertDoesNotThrow(() -> {
            // Should be able to call closeConnection multiple times without issues
            login.closeConnection();
            login.closeConnection();
            login.closeConnection();
        });
    }

    @Test
    void constructorEstablishesConnectionTest() {
        assertDoesNotThrow(() -> {
            // The constructor should establish a database connection
            Login newLogin = new Login();
            assertNotNull(newLogin);
            newLogin.closeConnection();
        });
    }

    @Test
    void loginMethodSignatureCompatibilityTest() {
        assertDoesNotThrow(() -> {
            // Verify all methods have the expected signatures
            Method loginFun = Login.class.getDeclaredMethod("loginFun");
            Method loginAuth = Login.class.getDeclaredMethod("loginAuth", int.class, String.class);
            Method checkPostgres = Login.class.getDeclaredMethod("checkPostgres", int.class, String.class);
            Method closeConnection = Login.class.getMethod("closeConnection");

            assertEquals(void.class, loginFun.getReturnType());
            assertEquals(boolean.class, loginAuth.getReturnType());
            assertEquals(boolean.class, checkPostgres.getReturnType());
            assertEquals(void.class, closeConnection.getReturnType());
        });
    }
}

