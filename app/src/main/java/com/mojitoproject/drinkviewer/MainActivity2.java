package com.mojitoproject.drinkviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private MyApi myApi;
    private MyAdapter myAdapter;
    private String BaseUrl = "https://mojitoproject.000webhostapp.com/connection_ftp/";
    private RecyclerView recyclerView;
    private ArrayList<ModelClass> modelClasses;
    private String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        modelClasses = new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        
        displayJson();
        
    }

    private void displayJson() {
        // use retrofit for http request
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(MyApi.class);

        //query -> dodatek do zapytania SQL (które można wykorzystać jako sortowanie, filtrowanie etc.)
        query = "where Percentage=19";

        Call<ArrayList<ModelClass>> arrayListCall = myApi.fetchData(query);
        arrayListCall.enqueue(new Callback<ArrayList<ModelClass>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelClass>> call, Response<ArrayList<ModelClass>> response) {
                modelClasses = response.body();
                // loop for recyclerview item form mysql
                for(int i = 0; i < modelClasses.size(); i++){
                    //set adapter
                    myAdapter = new MyAdapter(modelClasses, MainActivity2.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
                    //now set layout
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelClass>> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });
    }
}