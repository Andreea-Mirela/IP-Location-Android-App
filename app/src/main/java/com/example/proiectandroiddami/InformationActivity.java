package com.example.proiectandroiddami;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proiectandroiddami.database.model.AdresaIp;

public class InformationActivity extends AppCompatActivity {

    TextView textViewIpAddress;
    TextView textViewCountry;
    TextView textViewCountryCode;
    TextView textViewCity;
    TextView textViewRegion;
    TextView textViewCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initializareComponente();

        AdresaIp adresaIp = (AdresaIp) getIntent().getSerializableExtra("cod_informatii");

        if(adresaIp!=null) {
            textViewIpAddress.setText(adresaIp.getIp_address().toString());
            textViewCountry.setText(adresaIp.getCountry().toString());
            textViewCountryCode.setText(adresaIp.getCountry_code().toString());
            textViewCity.setText(adresaIp.getCity().toString());
            textViewRegion.setText(adresaIp.getRegion().toString());
            textViewCurrency.setText(adresaIp.getCurrency().toString());
        }
    }

    private void initializareComponente() {
        textViewIpAddress = findViewById(R.id.tv_information_ip_address);
        textViewCountry = findViewById(R.id.tv_information_country);
        textViewCountryCode = findViewById(R.id.tv_information_country_code);
        textViewCity = findViewById(R.id.tv_information_city);
        textViewRegion = findViewById(R.id.tv_information_region);
        textViewCurrency = findViewById(R.id.tv_information_currency);
    }
}