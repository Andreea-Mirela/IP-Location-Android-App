package com.example.proiectandroiddami;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroiddami.database.model.AdresaIp;
import com.google.android.material.textfield.TextInputEditText;

public class AdaugaAdresaIpActivity extends AppCompatActivity {

    public static final String CHEIE_ADRESA_IP = "cheie_adresa_ip";

    private TextInputEditText tietAdresaIp;
    private TextInputEditText tietOras;
    private Button btnAdaugaAdresaIp;

    private Intent intent;
    private AdresaIp adresaIp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_adresa_ip);

        initializareComponente();

        btnAdaugaAdresaIp.setOnClickListener(adaugaEvenimentClickBtnAdaugaAdresaIp());

        intent = getIntent();
        if(intent.hasExtra(CHEIE_ADRESA_IP)) {
            adresaIp = (AdresaIp) intent.getSerializableExtra(CHEIE_ADRESA_IP);
            buildViewsFromAdreseIp(adresaIp);
        }
    }

    private View.OnClickListener adaugaEvenimentClickBtnAdaugaAdresaIp() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validare()) {
                    buildAdresaIpFromWidgets();
                    intent.putExtra(CHEIE_ADRESA_IP, adresaIp);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void buildViewsFromAdreseIp(AdresaIp adresaIp) {
        if(adresaIp == null) {
            return;
        }
        if(adresaIp.getIp_address() != null) {
            tietAdresaIp.setText(adresaIp.getIp_address());
        }
        if(adresaIp.getCity() != null) {
            tietOras.setText(adresaIp.getCity());
        }
    }

    private void buildAdresaIpFromWidgets() {
        String adresa = tietAdresaIp.getText().toString();
        String oras = tietOras.getText().toString();

        if(adresaIp == null) {
            adresaIp = new AdresaIp(adresa, oras);
        } else {
            adresaIp.setIp_address(adresa);
            adresaIp.setCity(oras);
        }
    }

    private boolean validare() {
        //validare pentru campul name
        if (tietAdresaIp.getText() == null || tietAdresaIp.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.adresa_invalida,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (tietOras.getText() == null || tietOras.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.oras_adresa_invalid,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    private void initializareComponente() {
        tietAdresaIp = findViewById(R.id.tiet1_database_adresa_ip);
        tietOras = findViewById(R.id.tiet2_database_oras_adresa_ip);
        btnAdaugaAdresaIp = findViewById(R.id.btn_database_adauga_adresa_ip);
    }
}