package test;

import org.example.DB;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.Statement;

public class JenkinsTest {

    @BeforeAll
    static void setupSchema() throws Exception {
        try (Connection conn = DB.get(); Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE users (
                    username INT PRIMARY KEY,
                    balance INT,
                    password_hash VARCHAR(255)
                )
            """);
            stmt.execute("""
                CREATE TABLE transactions (
                    account INT,
                    amount INT,
                    description VARCHAR(50),
                    remarks VARCHAR(50),
                    transaction_time TIMESTAMP
                )
            """);
        }
    }
}
