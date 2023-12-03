package com.mojitoproject.drinkviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements DatabaseConnector.ConnectionListener{

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseConnector connector = new DatabaseConnector(this);
        connector.execute();
    }

    public void GetFromSQL(View view)
    {
//        TextView txt1 = (TextView) findViewById(R.id.DrinkViewer);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.getConnection();

            // Creating a statement
            Statement statement = connection.createStatement();

            // SQL query to retrieve data
            String sqlQuery = "SELECT id, name, age FROM employee_data";

            // Executing the query
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("age");
//                txt1.setText(name);
                // Print retrieved values
//                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onConnectionSuccess(Connection connection) {
        TextView txt1 = (TextView) findViewById(R.id.DrinkViewer);
        txt1.setText("test");
    }

    @Override
    public void onConnectionFailure(String error) {
        Toast.makeText(this, "Connection failed: " + error, Toast.LENGTH_SHORT).show();

//        TextView txt1 = (TextView) findViewById(R.id.DrinkViewer);
//        txt1.setText("eeeee");
    }
}