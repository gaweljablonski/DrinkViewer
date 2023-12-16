package com.mojitoproject.drinkviewer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import retrofit2.http.Tag;

public class FiltersPop extends Activity {

    private int width, height;

    private CheckBox wodkaCB, ginCB, rumCB, tequilaCB, metaxaCB;
    private CheckBox cukierCB, cytrynyCB, limonkiCB, jamCB;

    private String wodka, gin, rum, tequila, metaxa;
    private String cukier, cytryny, limonki, jam;

    private static boolean isWodka = false, isGin = false, isRum = false, isTequila = false, isMetaxa = false;
    private static boolean isCukier = false, isCytryny = false, isLimonki = false, isJam = false;
    private Button okB, clearB;
    private QueryClass queryClass;
    private MainActivity2 mainActivity2 = new MainActivity2();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filters_pop_window);

        // ustawia rozmiary filters_pop_window (popup window)
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*0.95), (int)(height*0.4));

        checkBoxesSetup();

        okB = findViewById(R.id.searchB);
        clearB = findViewById(R.id.clearB);

        // OK button w filters
        okB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "OK button clicked");
                onClickOKButtonFun();
            }
        });

        // reset button -> ustawia deafut'owe wartości CeckBox'ów (false/0)
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
                wodkaCB.setChecked(false);
                ginCB.setChecked(false);
                rumCB.setChecked(false);
                tequilaCB.setChecked(false);
                metaxaCB.setChecked(false);
                cukierCB.setChecked(false);
                cytrynyCB.setChecked(false);
                limonkiCB.setChecked(false);
                jamCB.setChecked(false);
            }
        });

        wodkaCB = findViewById(R.id.wodkaCB);
        ginCB = findViewById(R.id.ginCB);
        rumCB = findViewById(R.id.rumCB);
        tequilaCB = findViewById(R.id.tequilaCB);
        metaxaCB = findViewById(R.id.metaxaCB);

        cukierCB = findViewById(R.id.cukierCB);
        cytrynyCB = findViewById(R.id.cytrynyCB);
        limonkiCB = findViewById(R.id.limonkiCB);
        jamCB = findViewById(R.id.jamCB);

/*
        wodkaCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    wodka = "wodka";
                }
                else{
                    wodka = "";
                }
            }
        });

        ginCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    gin = "gin";
                }
                else{
                    gin = "";
                }
            }
        });
        rumCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rum = "rum";
                }
                else{
                    rum = "";
                }
            }
        });
        tequilaCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tequila = "tequila";
                }
                else{
                    tequila = "";
                }
            }
        });
        metaxaCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    metaxa = "metaxa";
                }
                else{
                    metaxa = "";
                }
            }
        });
        cukierCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cukier = "syrop cukrowy";
                }
                else{
                    cukier = "";
                }
            }
        });
        cytrynyCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cytryny = "cytryny";
                }
                else{
                    cytryny = "";
                }
            }
        });
        limonkiCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    limonki = "limonki";
                }
                else{
                    limonki = "";
                }
            }
        });
        jamCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jamCB.isChecked()){
                    jam = "dżem";
                }
                else{
                    jam = "";
                }
            }
        });
*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "on Destroy");
    }

    private void onClickOKButtonFun() {
        if(wodkaCB.isChecked()) isWodka=true;
        if(ginCB.isChecked()) isGin=true;
        if(rumCB.isChecked()) isRum=true;
        if(tequilaCB.isChecked()) isTequila=true;
        if(metaxaCB.isChecked()) isMetaxa=true;
        if(cukierCB.isChecked()) isCukier=true;
        if(cytrynyCB.isChecked()) isCytryny=true;
        if(limonkiCB.isChecked()) isLimonki=true;
        if(jamCB.isChecked()) isJam=true;

        if(isWodka) wodka = "wódk%";
        if(isGin) gin = "gin%";
        if(isRum) rum = "rum%";
        if(isTequila) tequila = "tequil%";
        if(isMetaxa) metaxa = "matax%";
        if(isCukier) cukier = "cukr%";
        if(isCytryny) cytryny = "cytryn%";
        if(isLimonki) limonki = "limonk%";
        if(isJam) jam = "dżem%";

        Log.e(TAG, "[RUM]: " + rum);

        queryClass = new QueryClass(wodka, gin, rum, tequila, metaxa, cukier, cytryny, limonki, jam);

        // przekazuje obiekt queryClass do MA2
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("Filters", queryClass);
        startActivity(intent);

        this.finish();
    }

    private void checkBoxesSetup(){
        if(isWodka) wodkaCB.setChecked(true);
        if(isGin) ginCB.setChecked(true);
        if(isRum) rumCB.setChecked(true);
        if(isTequila) tequilaCB.setChecked(true);
        if(isMetaxa) metaxaCB.setChecked(true);
        if(isCukier) cukierCB.setChecked(true);
        if(isCytryny) cytrynyCB.setChecked(true);
        if(isLimonki) limonkiCB.setChecked(true);
        if(isJam) jamCB.setChecked(true);
    }
}
