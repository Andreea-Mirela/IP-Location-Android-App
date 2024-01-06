package com.example.proiectandroiddami.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiectandroiddami.database.dao.AdresaIpDao;
import com.example.proiectandroiddami.database.dao.TaraDao;
import com.example.proiectandroiddami.database.model.AdresaIp;
import com.example.proiectandroiddami.database.model.Tara;

@Database(entities = {Tara.class, AdresaIp.class}, exportSchema = false, version = 1)
public abstract class DatabaseManager extends RoomDatabase {
    private static final String DAM_DB = "proiect_db";

    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, DAM_DB)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return databaseManager;
    }

    public abstract TaraDao getTaraDao();
    public abstract AdresaIpDao getAdresaIpDao();
}
