package solutis.livrariavirtual_solutis.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/livraria_virtual";
    private static final String USER = "root";
    private static final String PASSWORD = "nina";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver JDBC não encontrado.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
