package com.example.proiectandroiddami.activitatiJson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.json_proiect.R;
//import com.example.json_proiect.claseDinJson.Oras;
import com.example.proiectandroiddami.R;
import com.example.proiectandroiddami.claseJson.Oras;
import com.google.android.material.textfield.TextInputEditText;

public class FormActivity extends AppCompatActivity {

    public static final String ORAS_KEY = "oras_key";

    private TextInputEditText tietDenumireOras;
    private TextInputEditText tietRiscEpidemiologic;
    private TextInputEditText tietCodOras;

    private Button btnAdaugaOrasInForm;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initializareComponente();

        btnAdaugaOrasInForm.setOnClickListener(adaugaClickEvent());

        intent = getIntent();
    }

    private void initializareComponente() {
        tietDenumireOras = findViewById(R.id.tiet1_form);
        tietRiscEpidemiologic = findViewById(R.id.tiet2_form);
        tietCodOras = findViewById(R.id.tiet3_form);

        btnAdaugaOrasInForm = findViewById(R.id.btn_adauga_oras_form);
    }

    private View.OnClickListener adaugaClickEvent() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validare()) {
                    Oras oras = construiesteOras();
                    intent.putExtra(ORAS_KEY, oras);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private boolean validare() {
        if(tietDenumireOras.getText() == null || tietDenumireOras.getText().toString().trim().length() < 3)
        {
            Toast.makeText(getApplicationContext(), "Denumire invalida", Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietRiscEpidemiologic.getText() == null || tietRiscEpidemiologic.getText().toString().trim().length() < 5)
        {
            Toast.makeText(getApplicationContext(), "Riscul nu este unul valid", Toast.LENGTH_LONG).show();
            return false;
        }

        if(tietCodOras.getText() == null || tietCodOras.getText().toString().trim().length() < 2)
        {
            Toast.makeText(getApplicationContext(), "Cod invalid", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Oras construiesteOras() {
        String denumireOras = tietDenumireOras.getText().toString();
        String riscEpidemiologic = tietRiscEpidemiologic.getText().toString();
        String cod = tietCodOras.getText().toString();

        return new Oras(denumireOras,riscEpidemiologic,cod);
    }
}