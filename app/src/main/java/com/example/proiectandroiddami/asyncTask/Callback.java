package com.example.proiectandroiddami.asyncTask;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
