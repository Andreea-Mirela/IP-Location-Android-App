package com.example.proiectandroiddami.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectandroiddami.database.model.Tara;

import java.util.List;

@Dao
public interface TaraDao {
    @Query("select * from tari")
    List<Tara> getAll();

    @Insert
    long insert(Tara tara);

    @Update
    int update(Tara tara);

    @Delete
    int delete(Tara tara);

    @Query("select * from tari where suprafataTara =:suprafata")
    List<Tara> getAllRaport1(String suprafata);

    @Query("select * from tari where denumireTara =:denumire")
    Tara getTaraRaport2(String denumire);
}
