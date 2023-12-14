package com.mojitoproject.drinkviewer;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

public class FiltersPop extends Activity {

    private int width, height;

    private CheckBox wodkaCB, ginCB, rumCB, tequilaCB, metaxaCB;
    private CheckBox cukierCB, cytrynyCB, limonkiCB, jamCB;

    private String wodka, gin, rum, tequila, metaxa;
    private String cukier, cytryny, limonki, jam;
    private Button searchB, clearB;
    private QueryClass queryClass;
    private MainActivity2 mainActivity2 = new MainActivity2();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.filters_pop_window);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*0.95), (int)(height*0.4));

        checkBoxesFun();

        searchB = findViewById(R.id.searchB);
        clearB = findViewById(R.id.clearB);

        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainActivity2.showButtonFun();
            }
        });

        clearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wodka = "";
                gin = "";
                rum = "";
                tequila = "";
                metaxa = "";
                cukier = "";
                cytryny = "";
                limonki = "";
                jam = "";
            }
        });
    }
    private void checkBoxesFun(){
        wodkaCB = findViewById(R.id.wodkaCB);
        ginCB = findViewById(R.id.ginCB);
        rumCB = findViewById(R.id.rumCB);
        tequilaCB = findViewById(R.id.tequilaCB);
        metaxaCB = findViewById(R.id.metaxaCB);

        cukierCB = findViewById(R.id.cukierCB);
        cytrynyCB = findViewById(R.id.cytrynyCB);
        limonkiCB = findViewById(R.id.limonkiCB);
        jamCB = findViewById(R.id.jamCB);

        wodkaCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        queryClass = new QueryClass(wodka, gin, rum, tequila, metaxa, cukier, cytryny, limonki, jam);
        mainActivity2.setQueryClassFromFilters(queryClass);
    }
}
