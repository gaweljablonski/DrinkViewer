package com.mojitoproject.drinkviewer;

import android.widget.CheckBox;

/**
 * Klasa do przechowywania danych o szczegółach zapytania
 * Informacje z InltersPop -> MainActivity2
 */
public class QueryClass {
    private String wodka, gin, rum, tequila, metaxa;
    private String cukier, cytryny, limonki, jam;

    public QueryClass(String wodka, String gin, String rum, String tequila, String metaxa, String cukier, String cytryny, String limonki, String jam) {
        this.wodka = wodka;
        this.gin = gin;
        this.rum = rum;
        this.tequila = tequila;
        this.metaxa = metaxa;
        this.cukier = cukier;
        this.cytryny = cytryny;
        this.limonki = limonki;
        this.jam = jam;
    }

    public void setWodka(String wodka) {
        this.wodka = wodka;
    }

    public void setGin(String gin) {
        this.gin = gin;
    }

    public void setRum(String rum) {
        this.rum = rum;
    }

    public void setTequila(String tequila) {
        this.tequila = tequila;
    }

    public void setMetaxa(String metaxa) {
        this.metaxa = metaxa;
    }

    public void setCukier(String cukier) {
        this.cukier = cukier;
    }

    public void setCytryny(String cytryny) {
        this.cytryny = cytryny;
    }

    public void setLimonki(String limonki) {
        this.limonki = limonki;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getWodka() {
        return getterHelper(wodka, "OR");
    }

    public String getGin() {
        return getterHelper(gin, "OR");
    }

    public String getRum() {
        return getterHelper(rum, "OR");
    }

    public String getTequila() {
        return getterHelper(tequila, "OR");
    }

    public String getMetaxa() {
        return getterHelper(metaxa, "OR");
    }

    public String getCukier() {
        return getterHelper(cukier, "AND");
    }

    public String getCytryny() {
        return getterHelper(cytryny, "AND");
    }

    public String getLimonki() {
        return getterHelper(limonki, "AND");
    }

    public String getJam() {
        return getterHelper(jam, "AND");
    }

    private String getterHelper(String s, String operator){
        String query = "Ingredients LIKE '";
        query = query + s;
        query = query + "'";
        query = query + operator;
        if(s.equals(""))
            query = "";
        return query;
    }
}
