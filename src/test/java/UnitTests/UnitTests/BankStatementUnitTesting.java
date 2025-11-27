package UnitTests;

import org.example.BankStatement;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class BankStatementUnitTesting {

    //Declare a variable to hold the object we're testing
    private BankStatement statement;

    //Declare a variable for our temporary test file
    private File testFile;


    //Runs before each test method
    @BeforeEach

    //Set up method that might throw file error
    void setUp() throws IOException {

        //Create a fresh BankStatement object for testing
        statement = new BankStatement();

        //Create a temporary file object
        testFile = new File("test_statement.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Transfer to 1002 Debit 500 Success 2024-01-15 14:30:25");
        }
    }

    @Test
    void printHeaderTest() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        statement.printHeader();

        System.setOut(System.out);
        assertTrue(output.toString().contains("Description"));
    }

    @Test
    void printStatementLinesTest() throws FileNotFoundException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        statement.printStatementLines(new Scanner(testFile));

        System.setOut(System.out);
        assertTrue(output.toString().contains("Transfer to 1002"));
    }

    @Test
    void printFooterTest(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        statement.printFooter();
        System.setOut(System.out);
        assertTrue(output.toString().contains("----"));
    }


    @AfterEach
    void tearDown() {
        if (testFile.exists()) testFile.delete();
    }
}
