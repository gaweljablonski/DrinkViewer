package com.mojitoproject.drinkviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
    final private String BaseUrl = "https://mojitoproject.000webhostapp.com/connection_ftp/";
    private RecyclerView recyclerView;
    private ArrayList<ModelClass> modelClasses;
    private String query = "";
    private Button show, filters;
    private EditText restET;
    private QueryClass queryClass = new QueryClass("", "", "", "", "", "", "", "", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        modelClasses = new ArrayList<>();
        recyclerView = findViewById(R.id.rv);

        // Działanie Buttona SHOW/SEARCH w MA2
        show = (Button) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showButtonFun();
            }
        });

        // Działanie Buttona FILTERS w MA2
        filters = (Button) findViewById(R.id.filters);
        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, FiltersPop.class);
                startActivity(intent);
            }
        });

        restET = (EditText) findViewById(R.id.restET);

        // Wyświetla informacje pobrane z DB
        displayJson();
        
    }
    public void showButtonFunFromPop() {
        displayJson();
    }

    public void setQueryClassFromFilters(QueryClass qs){
        queryClass = qs;
    }
    private void showButtonFun() {
        query = "WHERE Name = '";
        query = query + restET.getText().toString();
        query = query + "' AND ";

        query = query + queryClass.getWodka();
        query = query + queryClass.getGin();
        query = query + queryClass.getRum();
        query = query + queryClass.getTequila();
        query = query + queryClass.getMetaxa();
        query = query + queryClass.getCukier();
        query = query + queryClass.getCytryny();
        query = query + queryClass.getLimonki();
        query = query + queryClass.getJam();

        query = query + " percentage like '%%'";

        // Check if no view has focus:
        // Hide Keyboard
        try {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        Toast.makeText(MainActivity2.this, query, Toast.LENGTH_LONG).show();
        displayJson();
    }

    private void displayJson() {
        // use retrofit for http request
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(MyApi.class);

        //query -> dodatek do zapytania SQL (które można wykorzystać jako sortowanie, filtrowanie etc.)
//        query = "";

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