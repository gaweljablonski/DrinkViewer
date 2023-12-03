package com.mojitoproject.drinkviewer;

import android.annotation.SuppressLint;

import java.sql.*;

public class ConnectionHelper {

    @SuppressLint("NewApi")

    public Connection getConnection() {

        String host = "jdbc:mysql://localhost:3306/employee101";
        String username = "root";
        String password = "";

        Connection connection = null;

        try {
            // load and register JDBC driver for MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing a connection to the database
            connection = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to the database!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } finally {
            // Close the connection in the finally block to ensure it's closed properly
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }


        return connection;
    }
}
