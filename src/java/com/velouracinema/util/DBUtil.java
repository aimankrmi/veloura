/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.velouracinema.util;

/**
 *
 * @author sitif
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/cinema_db"; // Replace with your database URL
        String username = "root"; // Replace with your MySQL username
        String password = "aiman123"; // Replace with your MySQL password

        Connection connection = null;

        try {
            // Load the MySQL JDBC driver (if not automatically loaded)
            Class.forName("com.mysql.cj.jdbc.Driver");  // Not always needed, check your driver version

            // Establish the connection
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connected to MySQL successfully!");
                // You can now use the connection to execute SQL queries
            } else {
                System.out.println("Failed to connect to MySQL.");
            }
            return connection;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("error connecting to MySQL: " + e.getMessage());
        } 
       return null;
    }

}
