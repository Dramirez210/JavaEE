package edu.uacm.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/db_jdbc?serverTimezone=America/Mexico_City";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2120";
    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
