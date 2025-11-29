package test;

import org.example.Transaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionRegression {

    private static final int TEST_ACC_NO = 1;
    private static final int RECEIVER_ACC_NO = 2;

    @Test
    void accountsExist() throws Exception {
        Transaction txn = new Transaction();
        assertTrue(txn.accountExistsForTest(TEST_ACC_NO));
        assertTrue(txn.accountExistsForTest(RECEIVER_ACC_NO));
    }

    @Test
    void senderHasEnoughBalance() throws Exception {
        Transaction txn = new Transaction();
        int balance = txn.getBalanceForTest(TEST_ACC_NO);
        assertTrue(balance >= 0);
    }
}
