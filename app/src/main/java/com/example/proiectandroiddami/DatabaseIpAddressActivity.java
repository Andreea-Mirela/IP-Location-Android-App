package com.example.proiectandroiddami;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroiddami.asyncTask.Callback;
import com.example.proiectandroiddami.database.model.AdresaIp;
import com.example.proiectandroiddami.database.service.AdresaIpService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DatabaseIpAddressActivity extends AppCompatActivity {

    private static final int ADAUGA_ADRESA_IP_REQUEST_CODE = 300;

    private ListView lvAdreseIp;
    private FloatingActionButton fabAddAdresaIp;

    private List<AdresaIp> adreseIpBd = new ArrayList<>();

    private AdresaIpService adresaIpService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_ip_address);

        initializareComponente();
    }

    private void initializareComponente() {
        lvAdreseIp = findViewById(R.id.lv_database_adrese_ip);
        fabAddAdresaIp = findViewById(R.id.fab_database_add_ip_address);
        adaugaAdapter();
        fabAddAdresaIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdaugaAdresaIpActivity.class);
                startActivityForResult(intent, ADAUGA_ADRESA_IP_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            AdresaIp adresaIp = (AdresaIp) data.getSerializableExtra(AdaugaAdresaIpActivity.CHEIE_ADRESA_IP);
            if (requestCode == ADAUGA_ADRESA_IP_REQUEST_CODE) {
                if (adresaIp != null) {
                    adreseIpBd.add(adresaIp);

                    ArrayAdapter adapter = (ArrayAdapter) lvAdreseIp.getAdapter();
                    adapter.notifyDataSetChanged();

                }

            }
        }
    }

    private void adaugaAdapter() {
        ArrayAdapter<AdresaIp> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                adreseIpBd);
        lvAdreseIp.setAdapter(adapter);
    }



    public Callback<AdresaIp> insertIntoDbCallback() {
        return new Callback<AdresaIp>() {
            @Override
            public void runResultOnUiThread(AdresaIp result) {
                if (result != null) {
                    adreseIpBd.add(result);

                    ArrayAdapter adapter = (ArrayAdapter) lvAdreseIp.getAdapter();
                    adapter.notifyDataSetChanged();


                }
            }
        };
    }

    private Callback<List<AdresaIp>> getAllFromDbCallback() {
        return new Callback<List<AdresaIp>>() {
            @Override
            public void runResultOnUiThread(List<AdresaIp> result) {
                Toast.makeText(getApplicationContext(),
                        R.string.conectare_reusita,
                        Toast.LENGTH_LONG)
                        .show();
                if (result != null) {
                    adreseIpBd.clear();
                    adreseIpBd.addAll(result);

                    ArrayAdapter adapter = (ArrayAdapter) lvAdreseIp.getAdapter();
                    adapter.notifyDataSetChanged();
                }

            }
        };
    }

    public Callback<AdresaIp> updateIntoDbCallback() {
        return new Callback<AdresaIp>() {
            @Override
            public void runResultOnUiThread(AdresaIp result) {
                if (result != null) {
                    for (AdresaIp adresaIp : adreseIpBd) {
                        if (adresaIp.getIdAdresaIp() == result.getIdAdresaIp()) {
                            adresaIp.setIp_address(result.getIp_address());
                            adresaIp.setCity(result.getCity());
                            break;
                        }
                    }

                    ArrayAdapter adapter = (ArrayAdapter) lvAdreseIp.getAdapter();
                    adapter.notifyDataSetChanged();

                }
            }
        };
    }

    public Callback<Integer> deleteFromDbCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    adreseIpBd.remove(position);
                    ArrayAdapter adapter = (ArrayAdapter) lvAdreseIp.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }
}
