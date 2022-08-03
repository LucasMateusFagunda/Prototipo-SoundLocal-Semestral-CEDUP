package com.lucasfagunda.semestral;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( //
                "jdbc:mysql://localhost:3306/soundlocal", //
                "root", //
                "");
    }
    
}
