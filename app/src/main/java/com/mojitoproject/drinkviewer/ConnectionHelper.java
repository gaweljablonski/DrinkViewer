package com.mojitoproject.drinkviewer;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;

import java.sql.*;

public class ConnectionHelper {

    @SuppressLint("NewApi")

//    String host = "jdbc:mysql://localhost:3306/employee101";
    String host = "jdbc:mariadb://localhost:3306/employee101";
    String username = "root";
    String password = "";

    public Connection getConnection() {



//        ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        Connection connection = null;

        try {
            // load and register JDBC driver for MySQL
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");

            // Establishing a connection to the database
            connection = DriverManager.getConnection(host, username, password);
            System.err.println("Connected to the database!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } finally {
//             Close the connection in the finally block to ensure it's closed properly
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.err.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }


        return connection;
    }

    public String getDriverName() throws SQLException {
        String db_class = null;
        try {
            db_class = DriverManager.getConnection(host, username, password).getClass().getName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return db_class;
    }
}
