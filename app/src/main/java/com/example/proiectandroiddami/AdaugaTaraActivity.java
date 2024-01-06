package com.example.proiectandroiddami;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroiddami.database.model.Tara;
import com.google.android.material.textfield.TextInputEditText;

public class AdaugaTaraActivity extends AppCompatActivity {

    public static final String CHEIE_TARA = "cheie_tara";

    private TextInputEditText tietDenumireTara;
    private TextInputEditText tietSuprafataTara;
    private TextInputEditText tietPopulatieTara;
    private Button btnAdaugaTara;

    private Intent intent;
    private Tara tara = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_tara);

        initializareComponente();

        btnAdaugaTara.setOnClickListener(adaugaEvenimentClickBtnAdaugaTara());

        intent = getIntent();
        if(intent.hasExtra(CHEIE_TARA)) {
            tara = (Tara) intent.getSerializableExtra(CHEIE_TARA);
            buildViewsFromTari(tara);
        }
    }

    private void buildViewsFromTari(Tara tara) {
        if(tara == null) {
            return;
        }
        if(tara.getDenumireTara() != null) {
            tietDenumireTara.setText(tara.getDenumireTara());
        }
        if(tara.getSuprafataTara() != null) {
            tietSuprafataTara.setText(tara.getSuprafataTara());
        }
        if(tara.getPopulatieTara() != null) {
            tietPopulatieTara.setText(tara.getPopulatieTara());
        }
    }

    private View.OnClickListener adaugaEvenimentClickBtnAdaugaTara() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validare()) {
                    buildTaraFromWidgets();
                    intent.putExtra(CHEIE_TARA, tara);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private boolean validare() {
        //validare pentru campul name
        if (tietDenumireTara.getText() == null || tietDenumireTara.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.denumire_tara_invalida,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (tietSuprafataTara.getText() == null || tietSuprafataTara.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.suprafata_tara_invalida,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (tietPopulatieTara.getText() == null || tietPopulatieTara.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.populatie_tara_invalida,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    private void initializareComponente() {
        tietDenumireTara = findViewById(R.id.tiet1_database_denumire_tara);
        tietSuprafataTara = findViewById(R.id.tiet2_database_suprafata_tara);
        tietPopulatieTara = findViewById(R.id.tiet3_database_populatie_tara);
        btnAdaugaTara = findViewById(R.id.btn_database_adauga_tara);
    }

    private void buildTaraFromWidgets() {
        //extragere nume
        String denumire = tietDenumireTara.getText().toString();
        String suprafata = tietSuprafataTara.getText().toString();
        String populatie = tietPopulatieTara.getText().toString();

        if(tara == null) {
            tara = new Tara(denumire, suprafata, populatie);
        } else {
            tara.setDenumireTara(denumire);
            tara.setSuprafataTara(suprafata);
            tara.setPopulatieTara(populatie);
        }
    }
}