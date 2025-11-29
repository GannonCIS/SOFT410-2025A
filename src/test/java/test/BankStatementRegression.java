package test;

import org.example.BankStatement;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BankStatementRegression {

    private static final int TEST_ACC_NO = 1;

    @Test
    void transactionsPrintWithoutError() {
        BankStatement bs = new BankStatement();
        System.setIn(new ByteArrayInputStream("\n".getBytes()));
        assertDoesNotThrow(() -> bs.printStatement(TEST_ACC_NO));
    }
}
