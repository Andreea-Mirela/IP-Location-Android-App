package com.example.proiectandroiddami;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.proiectandroiddami.database.model.Tara;
import com.example.proiectandroiddami.database.model.AdresaIp;

import java.util.List;

public class TariIpuri {

    @Embedded
    public Tara tara;

    @Relation(
            parentColumn = "idTara",
            entityColumn = "idAdresaIp"
    )
    public List<AdresaIp> adreseIp;

    public TariIpuri(Tara tara, List<AdresaIp> adreseIp) {
        this.tara = tara;
        this.adreseIp = adreseIp;
    }

    @Override
    public String toString() {
        return "TariIpuri{" +
                "tara=" + tara +
                ", adreseIp=" + adreseIp +
                '}';
    }
}
