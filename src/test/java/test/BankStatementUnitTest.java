package test;

import org.example.BankStatement;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankStatementUnitTest {

    @Test
    void printStatementPrintsHeader() {
        BankStatement statement = new BankStatement();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        System.setIn(new ByteArrayInputStream("\n".getBytes()));

        statement.printStatement(1);
        System.setOut(originalOut);

        String output = out.toString();
        assertTrue(output.contains("Description"));
    }
}
