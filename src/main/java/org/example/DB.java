package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    static Connection get() throws Exception {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/auth_system",
                "postgres",
                "2331"
        );
    }
}