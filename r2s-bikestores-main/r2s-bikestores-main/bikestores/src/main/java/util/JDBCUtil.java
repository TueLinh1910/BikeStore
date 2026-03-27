package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bikestores";
        String user = "root";
        String password = "191004";
        return DriverManager.getConnection(url, user, password);
    }
}