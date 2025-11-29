package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static final boolean IS_TEST = Boolean.getBoolean("test.env");

    public static Connection get() throws Exception {
        if ("true".equals(System.getProperty("test.env"))) {
            return DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        }
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/auth_system",
                "postgres",
                "2331"
        );
    }

}
