package com.example.proiectandroiddami.database.service;

import android.content.Context;

import com.example.proiectandroiddami.asyncTask.AsyncTaskRunner;
import com.example.proiectandroiddami.asyncTask.Callback;
import com.example.proiectandroiddami.database.DatabaseManager;
import com.example.proiectandroiddami.database.dao.AdresaIpDao;
import com.example.proiectandroiddami.database.model.AdresaIp;

import java.util.List;
import java.util.concurrent.Callable;

public class AdresaIpService {
    private final AdresaIpDao adresaIpDao;
    private final AsyncTaskRunner taskRunner;

    public AdresaIpService(Context context) {
        adresaIpDao = DatabaseManager.getInstance(context).getAdresaIpDao();
        taskRunner = new AsyncTaskRunner();
    }

    public void getAll(Callback<List<AdresaIp>> callback,final long idTara) {
        Callable<List<AdresaIp>> callable = new Callable<List<AdresaIp>>() {
            @Override
            public List<AdresaIp> call() {
                return adresaIpDao.getAll(idTara);
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    public void insert(Callback<AdresaIp> callback, final AdresaIp adresaIp) {
        Callable<AdresaIp> callable = new Callable<AdresaIp>() {
            @Override
            public AdresaIp call()  {
                if(adresaIp ==  null) {
                    return null;
                }
                long id = adresaIpDao.insert(adresaIp);
                if(id==-1) {
                    return null;
                }
                adresaIp.setIdAdresaIp(id);

                return adresaIp;
            }
        };
        taskRunner.executeAsync(callable,callback);
    }

    public void update(Callback<AdresaIp> callback, final AdresaIp adresaIp) {
        Callable<AdresaIp> callable = new Callable<AdresaIp>() {
            @Override
            public AdresaIp call() {
                if (adresaIp == null) {
                    return null;
                }
                int count = adresaIpDao.update(adresaIp);
                if (count != 1) {
                    return null;
                }
                return adresaIp;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void delete(Callback<Integer> callback, final AdresaIp adresaIp){
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if(adresaIp==null){
                    return null;
                }
                return adresaIpDao.delete(adresaIp);
            }
        };
        taskRunner.executeAsync(callable,callback);
    }
}
