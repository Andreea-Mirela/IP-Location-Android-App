package com.example.proiectandroiddami;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroiddami.asyncTask.Callback;
import com.example.proiectandroiddami.database.model.Tara;
import com.example.proiectandroiddami.database.service.TaraService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private static final int ADAUGA_TARA_REQUEST_CODE = 100;
    private static final int UPDATE_TARA_REQUEST_CODE = 200;

    private ListView lvTari;
    private FloatingActionButton fabAddTara;

    private List<Tara> tariBd = new ArrayList<>();
    private TaraService taraService;

    private ListView lvRaportTari;
    private List<Tara> tariBdRaport1 = new ArrayList<>();
    String suprafata = "123344";

    private TextView tvRaport2;
    String denumire = "Romania";

    Tara taraTextView;
    long idCurent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        initializareComponente();

        taraService = new TaraService(getApplicationContext());
        taraService.getAll(getAllFromDbCallback());

        taraService.getAllRaport1(getAllFromDbCallbackRaport1(), suprafata);

        taraService.getTaraRaport2(getAllFromDbCallbackRaport2(), denumire);

    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Tara tara = (Tara) data.getSerializableExtra(AdaugaTaraActivity.CHEIE_TARA);
            if (requestCode == ADAUGA_TARA_REQUEST_CODE) {
//            if (tara != null) {
//                tariBd.add(tara);
//
//                ArrayAdapter adapter = (ArrayAdapter) lvTari.getAdapter();
//                adapter.notifyDataSetChanged();
//            }
                taraService.insert(insertIntoDbCallback(), tara);
            } else if (requestCode == UPDATE_TARA_REQUEST_CODE) {
                taraService.update(updateIntoDbCallback(), tara);
            }
        }
    }

    private void initializareComponente() {
        lvTari = findViewById(R.id.lv_database_tari);
        fabAddTara = findViewById(R.id.fab_database_add);
        adaugaAdapter();
        fabAddTara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdaugaTaraActivity.class);
                startActivityForResult(intent, ADAUGA_TARA_REQUEST_CODE);
            }
        });

        lvTari.setOnItemClickListener(updateTaraEventListener());
        lvTari.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                taraService.delete(deleteFromDbCallback(position), tariBd.get(position));
                return true;
            }
        });

        lvRaportTari = findViewById(R.id.lv_database_tari_raport1);
        adaugaAdapterRaport1();

        tvRaport2 = findViewById(R.id.tv_lv_database_tari_raport2);

        tvRaport2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DatabaseIpAddressActivity.class);
                idCurent = taraTextView.getIdTara();
                intent.putExtra("idTara", idCurent );
                startActivity(intent);
            }
        });
    }

    private AdapterView.OnItemClickListener updateTaraEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AdaugaTaraActivity.class);
                intent.putExtra(AdaugaTaraActivity.CHEIE_TARA, tariBd.get(position));
                startActivityForResult(intent, UPDATE_TARA_REQUEST_CODE);
            }
        };
    }

    private void adaugaAdapter() {
        ArrayAdapter<Tara> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                tariBd);
        lvTari.setAdapter(adapter);
    }

    private void adaugaAdapterRaport1() {
        ArrayAdapter<Tara> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                tariBdRaport1);
        lvRaportTari.setAdapter(adapter);
    }


    private Callback<List<Tara>> getAllFromDbCallback() {
        return new Callback<List<Tara>>() {
            @Override
            public void runResultOnUiThread(List<Tara> result) {
                Toast.makeText(getApplicationContext(),
                        R.string.conectare_reusita,
                        Toast.LENGTH_LONG)
                        .show();
                if (result != null) {
                    tariBd.clear();
                    tariBd.addAll(result);

                    ArrayAdapter adapter = (ArrayAdapter) lvTari.getAdapter();
                    adapter.notifyDataSetChanged();
                }

            }
        };
    }

    private Callback<List<Tara>> getAllFromDbCallbackRaport1() {
        return new Callback<List<Tara>>() {
            @Override
            public void runResultOnUiThread(List<Tara> result) {
                if (result != null) {
                    tariBdRaport1.clear();
                    tariBdRaport1.addAll(result);

                    ArrayAdapter adapter = (ArrayAdapter) lvRaportTari.getAdapter();
                    adapter.notifyDataSetChanged();
                }

            }
        };
    }

    private Callback<Tara> getAllFromDbCallbackRaport2() {
        return new Callback<Tara>() {
            @Override
            public void runResultOnUiThread(Tara result) {
                if (result != null) {
                    tvRaport2.setText(result.toString());
                    taraTextView = result;
                }
            }
        };
    }

    public Callback<Tara> insertIntoDbCallback() {
        return new Callback<Tara>() {
            @Override
            public void runResultOnUiThread(Tara result) {
                if (result != null) {
                    tariBd.add(result);
                    if (result.getSuprafataTara().equals(suprafata)) {
                        tariBdRaport1.add(result);
                        ArrayAdapter adapter1 = (ArrayAdapter) lvRaportTari.getAdapter();
                        adapter1.notifyDataSetChanged();
                    }

                    ArrayAdapter adapter = (ArrayAdapter) lvTari.getAdapter();
                    adapter.notifyDataSetChanged();

                    if (result.getDenumireTara().equals(denumire)) {
                        tvRaport2.setText(result.toString());
                        taraTextView = result;
                    }

                }
            }
        };
    }

    public Callback<Tara> updateIntoDbCallback() {
        return new Callback<Tara>() {
            @Override
            public void runResultOnUiThread(Tara result) {
                if (result != null) {
                    for (Tara tara : tariBd) {
                        if (tara.getIdTara() == result.getIdTara()) {
                            tara.setDenumireTara(result.getDenumireTara());
                            tara.setSuprafataTara(result.getSuprafataTara());
                            tara.setPopulatieTara(result.getPopulatieTara());
                            break;
                        }
                    }
                    if (result.getSuprafataTara().equals(suprafata))
                        if (tariBdRaport1.contains(result))
                            for (Tara tara : tariBdRaport1) {
                                if (tara.getIdTara() == result.getIdTara()) {
                                    tara.setDenumireTara(result.getDenumireTara());
                                    tara.setSuprafataTara(result.getSuprafataTara());
                                    tara.setPopulatieTara(result.getPopulatieTara());
                                    ArrayAdapter adapter1 = (ArrayAdapter) lvRaportTari.getAdapter();
                                    adapter1.notifyDataSetChanged();
                                    break;
                                }
                            }
                        else tariBdRaport1.add(result);
                    ArrayAdapter adapter = (ArrayAdapter) lvTari.getAdapter();
                    adapter.notifyDataSetChanged();

                    if (result.getDenumireTara().equals(denumire)) {
                        tvRaport2.setText(result.toString());
                        taraTextView = result;
                    }
                }
            }
        };
    }

    public Callback<Integer> deleteFromDbCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    Tara tara = tariBd.get(position);

                    for (Tara tara1 : tariBdRaport1) {
                        if (tara1.getIdTara() == tara.getIdTara()) {
                            tariBdRaport1.remove(tara1);
                            ArrayAdapter adapter1 = (ArrayAdapter) lvRaportTari.getAdapter();
                            adapter1.notifyDataSetChanged();
                            break;
                        }
                    }
                    if (tara.getIdTara() == taraTextView.getIdTara()) {
                        tvRaport2.setText("");
                    }
                    tariBd.remove(position);
                    ArrayAdapter adapter = (ArrayAdapter) lvTari.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }
}