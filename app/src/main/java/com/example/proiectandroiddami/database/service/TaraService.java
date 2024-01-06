package com.example.proiectandroiddami.database.service;

import android.content.Context;

import com.example.proiectandroiddami.asyncTask.AsyncTaskRunner;
import com.example.proiectandroiddami.asyncTask.Callback;
import com.example.proiectandroiddami.database.DatabaseManager;
import com.example.proiectandroiddami.database.dao.TaraDao;
import com.example.proiectandroiddami.database.model.Tara;

import java.util.List;
import java.util.concurrent.Callable;

public class TaraService {

    private final TaraDao taraDao;
    private final AsyncTaskRunner taskRunner;

    public TaraService(Context context) {
        taraDao = DatabaseManager.getInstance(context).getTaraDao();
        taskRunner = new AsyncTaskRunner();
    }

    public void getAll(Callback<List<Tara>> callback) {
        Callable<List<Tara>> callable = new Callable<List<Tara>>() {
            @Override
            public List<Tara> call() {
                return taraDao.getAll();
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    public void getAllRaport1(Callback<List<Tara>> callback, final String suprafata) {
        Callable<List<Tara>> callable = new Callable<List<Tara>>() {
            @Override
            public List<Tara> call() {
                return taraDao.getAllRaport1(suprafata);
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    public void getTaraRaport2(Callback<Tara> callback, final String denumire) {
        Callable<Tara> callable = new Callable<Tara>() {
            @Override
            public Tara call() {
                return taraDao.getTaraRaport2(denumire);
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    public void insert(Callback<Tara> callback, final Tara tara) {
        Callable<Tara> callable = new Callable<Tara>() {
            @Override
            public Tara call()  {
                if(tara ==  null) {
                    return null;
                }
                long id = taraDao.insert(tara);
                if(id==-1) {
                    return null;
                }
                tara.setIdTara(id);
                return tara;
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    public void update(Callback<Tara> callback, final Tara tara) {
        Callable<Tara> callable = new Callable<Tara>() {
            @Override
            public Tara call() throws Exception {
                if(tara == null) {
                    return null;
                }

                int count = taraDao.update(tara);
                if(count != 1) {
                    return null;
                }
                return tara;
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    public void delete(Callback<Integer> callback, final Tara tara) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if(tara == null) {
                    return -1;
                }
                return taraDao.delete(tara);
            }
        };
        taskRunner.executeAsync(callable,callback);
    }


}
