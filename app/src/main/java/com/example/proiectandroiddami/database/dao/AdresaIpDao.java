package com.example.proiectandroiddami.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectandroiddami.database.model.AdresaIp;

import java.util.List;

@Dao
public interface AdresaIpDao {
    @Query("select * from adreseIp where idFkTara=:idTara")
    List<AdresaIp> getAll(long idTara);

    @Insert
    long insert(AdresaIp adresaIp);

    @Update
    int update(AdresaIp adresaIp);

    @Delete
    int delete(AdresaIp adresaIp);
}
