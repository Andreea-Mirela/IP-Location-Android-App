package com.example.proiectandroiddami.claseJson;


import com.example.proiectandroiddami.database.model.Tara;

import java.io.Serializable;

public class Continent implements Serializable {

    private String numeContinent;
    private String suprafataContinent;
    private String densitate;
    public Tara tara; //un obiect de tipul tara

    public Continent(String numeContinent, String suprafataContinent, String densitate) {
        this.numeContinent = numeContinent;
        this.suprafataContinent = suprafataContinent;
        this.densitate = densitate;
    }

    public Continent(String numeContinent, String suprafataContinent, String densitate, Tara tara) {
        this.numeContinent = numeContinent;
        this.suprafataContinent = suprafataContinent;
        this.densitate = densitate;
        this.tara = tara;
    }

    public String getNumeContinent() {
        return numeContinent;
    }

    public void setNumeContinent(String numeContinent) {
        this.numeContinent = numeContinent;
    }

    public String getSuprafataContinent() {
        return suprafataContinent;
    }

    public void setSuprafataContinent(String suprafataContinent) {
        this.suprafataContinent = suprafataContinent;
    }

    public String getDensitate() {
        return densitate;
    }

    public void setDensitate(String densitate) {
        this.densitate = densitate;
    }

    public Tara getTara() {
        return tara;
    }

    public void setTara(Tara tara) {
        this.tara = tara;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "denumire='" + numeContinent + '\'' +
                ", suprafata=" + suprafataContinent +
                ", densitate='" + densitate + '\'' +
                ", tara=" + tara +
                '}';
    }
}


