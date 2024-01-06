package com.example.proiectandroiddami.database.dao;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.proiectandroiddami.TariIpuri;
import com.example.proiectandroiddami.database.model.AdresaIp;

import java.util.List;


public interface TariIpuriDao {
    @Transaction
    @Query("select * from tari")
    public List<TariIpuri> getTariCuIpuri();

    @Delete
    void delete(AdresaIp adresaIp);
}
