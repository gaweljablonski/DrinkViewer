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

    public String getWodka() {
        return wodka;
    }

    public void setWodka(String wodka) {
        this.wodka = wodka;
    }

    public String getGin() {
        return gin;
    }

    public void setGin(String gin) {
        this.gin = gin;
    }

    public String getRum() {
        return rum;
    }

    public void setRum(String rum) {
        this.rum = rum;
    }

    public String getTequila() {
        return tequila;
    }

    public void setTequila(String tequila) {
        this.tequila = tequila;
    }

    public String getMetaxa() {
        return metaxa;
    }

    public void setMetaxa(String metaxa) {
        this.metaxa = metaxa;
    }

    public String getCukier() {
        return cukier;
    }

    public void setCukier(String cukier) {
        this.cukier = cukier;
    }

    public String getCytryny() {
        return cytryny;
    }

    public void setCytryny(String cytryny) {
        this.cytryny = cytryny;
    }

    public String getLimonki() {
        return limonki;
    }

    public void setLimonki(String limonki) {
        this.limonki = limonki;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
