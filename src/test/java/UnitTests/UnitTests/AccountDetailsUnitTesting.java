package UnitTests;

import org.example.AccountDetails;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountDetailsUnitTesting {
    private AccountDetails details;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        details = new AccountDetails();

        // Create the directory and file that AccountDetails expects
        File dbDir = new File("db");
        if (!dbDir.exists()) {
            dbDir.mkdir();
        }

        testFile = new File("db/userDB.txt");  // Use the actual file path
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("1001 John Doe 1990-01-01 Male Address 555-1234 email@test.com C12345");
        }
    }

    @Test
    void accountDetailsFunTest() {
        assertDoesNotThrow(() -> details.accountDetailsFun(1001));
    }

    @AfterEach
    void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
        // Clean up db directory if empty
        File dbDir = new File("db");
        if (dbDir.exists() && dbDir.list().length == 0) {
            dbDir.delete();
        }
    }

}
