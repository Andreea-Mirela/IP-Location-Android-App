package com.example.proiectandroiddami.claseJson;

import java.io.Serializable;

public class Oras implements Serializable {

    private String denumireOras;
    private String riscEpidemiologic;
    private String cod;

    public Oras(String denumireOras, String riscEpidemiologic, String cod) {
        this.denumireOras = denumireOras;
        this.riscEpidemiologic = riscEpidemiologic;
        this.cod = cod;
    }


    public String getDenumireOras() {
        return denumireOras;
    }

    public void setDenumireOras(String denumireOras) {
        this.denumireOras = denumireOras;
    }

    public String getRiscEpidemiologic() {
        return riscEpidemiologic;
    }

    public void setRiscEpidemiologic(String riscEpidemiologic) {
        this.riscEpidemiologic = riscEpidemiologic;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "Oras{" +
                "denumire='" + denumireOras + '\'' +
                ", risc epidemiologic=" + riscEpidemiologic +
                ", cod='" + cod +
                '}';
    }
}
