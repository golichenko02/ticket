package org.hillel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DatabaseUtil {

    private static String url = "jdbc:postgresql://localhost:5432/tickets";
    private static String username = "postgres";
    private static String password = "root";

    private DatabaseUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}