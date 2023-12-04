package com.mojitoproject.drinkviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        variubles();
    }

    public String GetFromSQL(View view)
    {
        String name2 = null;
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

                // Print retrieved values
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);

                name2 = name;
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return name2;
    }
    public void variubles(){
        TextView pos1 = (TextView) findViewById(R.id.pos1);
        pos1.setText("test");
        //pos1.setText(GetFromSQL(null));
    }
}