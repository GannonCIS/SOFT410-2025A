package test;

import org.example.AccountDetails;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class AccountDetailsRegression {

    private static final int TEST_ACC_NO = 1;

    @Test
    void getUserReturnsValidArray() throws Exception {
        AccountDetails acc = new AccountDetails();

        Method method = AccountDetails.class.getDeclaredMethod("getUser", int.class);
        method.setAccessible(true);

        String[] result = (String[]) method.invoke(acc, TEST_ACC_NO);

        assertNotNull(result);
        assertEquals(8, result.length);
    }
}
