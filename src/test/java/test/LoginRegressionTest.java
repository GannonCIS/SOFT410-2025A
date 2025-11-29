package test;

import org.example.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRegressionTest {

    private static final int TEST_ACC_NO = 1;
    private static final String TEST_PASS = "123";

    @Test
    void validLogin() throws Exception {
        Login login = new Login();
        assertTrue(login.checkPassword(TEST_ACC_NO, TEST_PASS));
    }

    @Test
    void invalidPassword() throws Exception {
        Login login = new Login();
        assertFalse(login.checkPassword(TEST_ACC_NO, "wrongpass"));
    }

    @Test
    void nonExistentAccount() throws Exception {
        Login login = new Login();
        assertFalse(login.checkPassword(999, TEST_PASS));
    }
}
