package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface BaseDatabase {
    String BASE_URL = "jdbc:postgresql://localhost:5432/LINKEDIN";
    String USERNAME = "postgres";
    String PASSWORD = "2401";

    static Connection getConnection() {
        try {
            return DriverManager.getConnection(BASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
