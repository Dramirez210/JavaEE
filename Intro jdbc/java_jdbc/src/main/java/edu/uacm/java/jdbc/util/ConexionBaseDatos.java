package edu.uacm.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/db_jdbc?serverTimezone=America/Mexico_City";
    private static String username = "root";
    private static String password = "2120";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(url, username,password);
        }
        return connection;
    }
}
