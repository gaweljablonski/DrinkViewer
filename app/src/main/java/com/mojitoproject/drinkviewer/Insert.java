package com.mojitoproject.drinkviewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Insert extends AppCompatActivity {

    private EditText NameET, DescriptionET, IngredientsET, PercentageET;
    private Button add, viewdata;
    private MyApi myApi;
    private String BaseUrl = "https://mojitoproject.000webhostapp.com/connection_ftp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);

        Log.wtf("LOG WTF INSERT", "PIJKA");

        NameET = (EditText) findViewById(R.id.Name);
        DescriptionET = (EditText) findViewById(R.id.Description);
        IngredientsET = (EditText) findViewById(R.id.Ingredients);
        PercentageET = (EditText) findViewById(R.id.Percentage);

        // Button ADD/SUMMIT
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        // Button ViewData
        viewdata = (Button) findViewById(R.id.view);
        viewdata.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Insert.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }
    private void insertData() {
        int percentage = 0;
        String name = NameET.getText().toString();
        String description = DescriptionET.getText().toString();
        String ingredients = IngredientsET.getText().toString();
        try {
            // sprawdza czy pole jest zapisane jako liczba
             percentage = Integer.parseInt(PercentageET.getText().toString());
        } catch (NumberFormatException e) {
//            throw new RuntimeException(e);
            Toast.makeText(Insert.this, "Data not correct", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // use retrofit for http request
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            myApi = retrofit.create(MyApi.class);
            Call<ModelClass> modelClassCall = myApi.insertData(name, description, ingredients, percentage, "Drineczki");
            modelClassCall.enqueue(new Callback<ModelClass>() {
                @Override
                public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                    Toast.makeText(Insert.this, "Data successfully inserted", Toast.LENGTH_SHORT).show();
                    NameET.setText("");
                    DescriptionET.setText("");
                    IngredientsET.setText("");
                    PercentageET.setText("");
                }

                @Override
                public void onFailure(Call<ModelClass> call, Throwable t) {
                    Toast.makeText(Insert.this, "Failed to inserted", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e) {
//            throw new RuntimeException(e);
            Toast.makeText(Insert.this, "Data not correct", Toast.LENGTH_SHORT).show();
        }
    }


}