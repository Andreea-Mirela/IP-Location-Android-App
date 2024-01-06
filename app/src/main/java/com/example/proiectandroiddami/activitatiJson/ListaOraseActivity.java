package com.example.proiectandroiddami.activitatiJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.json_proiect.R;
//import com.example.json_proiect.claseDinJson.Oras;
//import com.example.json_proiect.claseDinJson.OrasAdapter;

import com.example.proiectandroiddami.R;
import com.example.proiectandroiddami.claseJson.Oras;
import com.example.proiectandroiddami.claseJson.OrasAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaOraseActivity extends AppCompatActivity {

    private static final int ADAUGA_ORAS_REQUEST_CODE=111;

    private Button buttonAdaugaOras;
    private ListView lvOrase;
    private List<Oras> listaOrase = new ArrayList<>();

    Oras oras1 = new Oras("Venetia","RIDICAT","28" );
    Oras oras2 = new Oras("Madrid","MEDIU","0012" );
    Oras oras3 = new Oras("Targoviste","SCAZUT","987-" );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_orase);
        initializareComponente();

        listaOrase.add(oras1);
        listaOrase.add(oras2);
        listaOrase.add(oras3);

        listaOraseAdapter();

        buttonAdaugaOras.setOnClickListener(buttonAdaugaOrasEvenimentClick());
    }

    private void initializareComponente()
    {
        buttonAdaugaOras = findViewById(R.id.btn_adauga_oras);
        lvOrase = findViewById(R.id.lv_orase);
    }

    private View.OnClickListener buttonAdaugaOrasEvenimentClick()
    {
        return new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivityForResult(intent, ADAUGA_ORAS_REQUEST_CODE);
            }
        };
    }

    private void listaOraseAdapter() {
        OrasAdapter adapter = new OrasAdapter(getApplicationContext(), R.layout.lv_orase,listaOrase,getLayoutInflater());
        lvOrase.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADAUGA_ORAS_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Oras oras = (Oras) data.getSerializableExtra(FormActivity.ORAS_KEY);
            if(oras != null) {
                Toast.makeText(getApplicationContext(), "Oras adaugat", Toast.LENGTH_LONG).show();
                listaOrase.add(oras);
            }
            ArrayAdapter adapter = (ArrayAdapter) lvOrase.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

}