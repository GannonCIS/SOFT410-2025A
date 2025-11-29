package test;

import org.example.BalanceRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BalanceRepoUnitTest {

    private final BalanceRepository repo = new BalanceRepository();

    @Test
    void getBalanceDoesNotThrow() {
        assertDoesNotThrow(() -> repo.getBalance(1001));
    }

    @Test
    void updateBalanceDoesNotThrow() {
        assertDoesNotThrow(() -> repo.updateBalance(1001, 500));
    }

    @Test
    void transferDoesNotThrow() {
        assertDoesNotThrow(() -> repo.transfer(1001, 1002, 1));
    }
}
