package com.example.proiectandroiddami.activitatiJson;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.json_proiect.Network.HttpManager;
//import com.example.json_proiect.R;
//import com.example.json_proiect.asyncTask.AsyncTaskRunner;
//import com.example.json_proiect.asyncTask.Callback;
//import com.example.json_proiect.claseDinJson.Continent;
//import com.example.json_proiect.claseDinJson.ContinentAdapter;
//import com.example.json_proiect.claseDinJson.ContinentJsonParser;
//import com.example.json_proiect.claseDinJson.Oras;
//import com.example.json_proiect.claseDinJson.Tara;

import com.example.proiectandroiddami.R;
import com.example.proiectandroiddami.asyncTask.AsyncTaskRunner;
import com.example.proiectandroiddami.asyncTask.Callback;
import com.example.proiectandroiddami.claseJson.Continent;
import com.example.proiectandroiddami.claseJson.ContinentAdapter;
import com.example.proiectandroiddami.claseJson.ContinentJsonParser;
import com.example.proiectandroiddami.claseJson.Oras;
import com.example.proiectandroiddami.database.model.Tara;
import com.example.proiectandroiddami.network.HttpManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ListaRezultateActivity extends AppCompatActivity {

    private static final String URL_REZULTATE_CONTINENTE = "https://jsonkeeper.com/b/IQME";

    Intent intent;

    private Button buttonMeniuPrincipal;
    private ListView lvRezultate;
    private List<Continent> listaContinente = new ArrayList<>();

    Oras oras = new Oras("Bruxelles", "RIDICAT", "765");
    Tara tara = new Tara("Belgia", "30.528 km^²", "11.071.483 locuitori");
    Continent continent = new Continent("Europa", "10.180.000 km^2", "72.9 locuitori/km^2");

    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

    private List<Oras> listaOrase=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rezultate);
        preiaContinenteDinHttp();

        //listaContinente.add(continent);

        initializareComponente();

        listaContinenteAdapter();

        buttonMeniuPrincipal.setOnClickListener( buttonMeniuPrincipalEvenimentClick());



        intent = getIntent();


    }

    private void initializareComponente() {
        buttonMeniuPrincipal = findViewById(R.id.btn_inapoi_meniu);
        lvRezultate = findViewById(R.id.lv_continente_json);
    }


    private void listaContinenteAdapter() {
        ContinentAdapter adapter = new ContinentAdapter(getApplicationContext(), R.layout.lv_rezultate,listaContinente,getLayoutInflater());
        lvRezultate.setAdapter(adapter);
        notifyAdapter();
    }


    private View.OnClickListener buttonMeniuPrincipalEvenimentClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvRezultate.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void preiaContinenteDinHttp() {
        Callable<String> asyncOperation = new HttpManager(URL_REZULTATE_CONTINENTE);
        Callback<String> mainThreadOperation = getMainThreadOperationForContinents();
        asyncTaskRunner.executeAsync(asyncOperation,mainThreadOperation);
    }

    private Callback<String> getMainThreadOperationForContinents() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(getApplicationContext(), "Fisierul Json a fost accesat cu succes!", Toast.LENGTH_SHORT).show();
                listaContinente.addAll(ContinentJsonParser.fromJson(result));
                notifyAdapter();
            }
        };
    }

    private List<Oras> preiaListaOrase()
    {
        List<Oras> listaOrase = new ArrayList<>();

        for (Continent continent: listaContinente) {
            String denumireOras = continent.getTara().getOras().getDenumireOras();
            String riscEpidemiologic = continent.getTara().getOras().getRiscEpidemiologic();
            String cod = continent.getTara().getOras().getCod();

            Oras oras = new Oras(denumireOras,riscEpidemiologic,cod);
            Log.i("tago",oras.toString());
            listaOrase.add(oras);
        }

        return listaOrase;
    }
}