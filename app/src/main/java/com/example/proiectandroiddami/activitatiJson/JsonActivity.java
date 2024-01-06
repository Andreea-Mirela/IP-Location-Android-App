package com.example.proiectandroiddami.activitatiJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.json_proiect.R;
//import com.example.json_proiect.claseDinJson.Oras;

import com.example.proiectandroiddami.R;
import com.example.proiectandroiddami.claseJson.Oras;

import java.util.List;

public class JsonActivity extends AppCompatActivity {

    private ImageView imageViewOrase;
    private ImageView imageViewContinente; //rezultatele finale

    private List<Oras> listaOrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        imageViewOrase = findViewById(R.id.orase_titlu_main);
        imageViewOrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_orase = new Intent(getApplicationContext(), ListaOraseActivity.class);
                startActivity(intent_orase);
            }
        });

        imageViewContinente = findViewById(R.id.continente_main_imagine);
        imageViewContinente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_rezultate = new Intent(getApplicationContext(), ListaRezultateActivity.class);
                startActivity(intent_rezultate);
            }
        });
    }
}