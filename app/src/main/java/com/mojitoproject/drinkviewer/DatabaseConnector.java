package com.mojitoproject.drinkviewer;

import android.os.AsyncTask;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector extends AsyncTask<Void, Void, Connection> {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee101";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private ConnectionListener connectionListener;

    public interface ConnectionListener {
        void onConnectionSuccess(Connection connection);
        void onConnectionFailure(String error);
    }

    public DatabaseConnector(ConnectionListener listener) {
        this.connectionListener = listener;
    }

    @Override
    protected Connection doInBackground(Void... voids) {
        Connection connection = null;
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            Log.e("DatabaseConnector", "Connection error: " + e.getMessage());
        }
        return connection;
    }

    @Override
    protected void onPostExecute(Connection connection) {
        if (connection != null) {
            connectionListener.onConnectionSuccess(connection);
        } else {
            connectionListener.onConnectionFailure("Failed to connect to the database");
        }
    }
}
