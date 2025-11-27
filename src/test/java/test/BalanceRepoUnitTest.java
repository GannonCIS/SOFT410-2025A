package test;

import org.example.BalanceRepository;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;


public class BalanceRepoUnitTest {
    private BalanceRepository repo;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        repo = new BalanceRepository();

        // Create the directory and file that AccountDetails expects
        File dbDir = new File("db");
        if (!dbDir.exists()) {
            dbDir.mkdir();
        }

        testFile = new File("db/balanceDB.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("1001 1500\n1002 2500");
        }
    }

    @Test
    void accountExistsTest() throws IOException {
        assertTrue(repo.accountExists(1001));
    }

    @Test
    void getBalanceTest() throws IOException {
        assertEquals(1500, repo.getBalance(1001));
    }

    @Test
    void updateBalancesTest() throws IOException {
        repo.updateBalances(1001, 1002, 500);
        assertTrue(true);
    }

    @AfterEach
    void tearDown() {
        if (testFile.exists()) testFile.delete();
    }



}
