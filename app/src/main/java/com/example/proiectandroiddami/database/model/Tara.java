package com.example.proiectandroiddami.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.proiectandroiddami.claseJson.Oras;

import java.io.Serializable;

@Entity(tableName = "tari")
public class Tara implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTara")
    private long idTara;

    @ColumnInfo(name = "denumireTara")
    private String denumireTara;
    @ColumnInfo(name = "suprafataTara")
    private String suprafataTara;
    @ColumnInfo(name = "populatieTara")
    private String populatieTara;
    @Ignore
    private Oras oras;

    public Tara(long idTara, String denumireTara, String suprafataTara, String populatieTara) {
        this.idTara = idTara;
        this.denumireTara = denumireTara;
        this.suprafataTara = suprafataTara;
        this.populatieTara = populatieTara;
    }

    @Ignore
    public Tara(String denumireTara, String suprafataTara, String populatieTara) {
        this.denumireTara = denumireTara;
        this.suprafataTara = suprafataTara;
        this.populatieTara = populatieTara;
    }
    @Ignore
    public Tara(String denumireTara, String suprafataTara, String populatieTara, Oras oras) {
        this.denumireTara = denumireTara;
        this.suprafataTara = suprafataTara;
        this.populatieTara = populatieTara;
        this.oras = oras;
    }

    public String getDenumireTara() {
        return denumireTara;
    }

    public void setDenumireTara(String denumireTara) {
        this.denumireTara = denumireTara;
    }

    public String getSuprafataTara() {
        return suprafataTara;
    }

    public void setSuprafataTara(String suprafataTara) {
        this.suprafataTara = suprafataTara;
    }

    public String getPopulatieTara() {
        return populatieTara;
    }

    public void setPopulatieTara(String populatieTara) {
        this.populatieTara = populatieTara;
    }

    public Oras getOras() {
        return oras;
    }

    public void setOras(Oras oras) {
        this.oras = oras;
    }

    public long getIdTara() {
        return idTara;
    }

    public void setIdTara(long idTara) {
        this.idTara = idTara;
    }

    @Override
    public String toString() {
        if(oras!=null)
        return "Tara{" +
                "denumire='" + denumireTara + '\'' +
                ", suprafata=" + suprafataTara +
                ", populatie='" + populatieTara + '\'' +
                ", oras=" + oras +
                '}';
        else
            return "Tara{" +
                    "denumire=" + denumireTara  +
                    ", suprafata=" + suprafataTara +
                    ", populatie=" + populatieTara + "}";
    }
}
