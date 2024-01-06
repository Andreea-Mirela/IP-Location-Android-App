package com.example.proiectandroiddami;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroiddami.activitatiJson.JsonActivity;
import com.example.proiectandroiddami.asyncTask.AsyncTaskRunner;
import com.example.proiectandroiddami.asyncTask.Callback;
import com.example.proiectandroiddami.database.model.AdresaIp;
import com.example.proiectandroiddami.network.HttpManager;
import com.example.proiectandroiddami.util.AdresaIpJsonParser;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    public static final String FISIER_PREFERINTE_PT_IP = "fisierPreferintePtIP";
    ImageView ivRevenire;

    private TextInputEditText tietIp;
    private Button btnAfiseazaRezultat;
    GridLayout gridLayout;
    LinearLayout llQuizz;
    LinearLayout llJson;
    LinearLayout llDatabase;
    LinearLayout llClasament;

    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
    private static final String URL_IP1 = "https://ipfind.co/?ip=";
    private static final String URL_IP2 = "&auth=f5fdd6f6-c669-4e57-9b31-ccfd05eb984f";
    String URL_IP_FINAL;

    String continent_quizz;
    int cod_quizz;
    AdresaIp adresaIp;

    private ImageView imgAfiseazaInformatii;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializareComponente();


        btnAfiseazaRezultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IP = String.valueOf(tietIp.getText());
                URL_IP_FINAL = URL_IP1 + IP + URL_IP2;
                preiaAdresaIpDinHttp();

                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("ip",IP);
                editor.apply();

            }
        });


        ivRevenire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfilActivity.class);
                startActivity(intent);
            }
        });



        llQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        continent_quizz = adresaIp.getContinent();

        switch (continent_quizz) {
            case "Europe":
                cod_quizz = 0;
                break;
            case "Africa":
                cod_quizz = 1;
                break;
            case "North America":
                cod_quizz = 2;
                break;
            case "Sud America":
                cod_quizz = 3;
                break;
            case "Asia":
                cod_quizz = 4;
                break;
            case "Australia":
            case "Oceania":
                cod_quizz = 5;
                break;
            default:
                cod_quizz = 0;

        }
                Intent intent = new Intent(getApplicationContext(),QuizzActivity.class);
                intent.putExtra("cod", cod_quizz);
                startActivity(intent);
            }
        });

        imgAfiseazaInformatii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InformationActivity.class);
                intent.putExtra("cod_informatii", adresaIp);
                startActivity(intent);
            }
        });

        llJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JsonActivity.class);
                startActivity(intent);
            }
        });

        sharedPreferences();

        llDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intent);
            }
        });

        llClasament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinalActivity.class);
                startActivity(intent);
            }
        });
    }



    private void sharedPreferences() {
        tietIp.setText(preferences.getString("ip",""));
    }

    private void initializareComponente() {
        ivRevenire = findViewById(R.id.main_iv_revenire);
        tietIp = findViewById(R.id.tiet_ip);
        btnAfiseazaRezultat = findViewById(R.id.button_afiseaza_rezultat);
        gridLayout = findViewById(R.id.mida_grid);
        llQuizz = findViewById(R.id.main_ll_quizz);

        imgAfiseazaInformatii = findViewById(R.id.img_main_informatii_despre);

        llJson = findViewById(R.id.ll_json);
        llDatabase = findViewById(R.id.ll_database);
        llClasament = findViewById(R.id.ll_final);

        preferences = getSharedPreferences(FISIER_PREFERINTE_PT_IP,MODE_PRIVATE);

        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void preiaAdresaIpDinHttp() {
        Callable<String> asyncOperation = new HttpManager(URL_IP_FINAL);
        Callback<String> mainThreadOperation = getMainThreadOperationForIpAddress();
        asyncTaskRunner.executeAsync(asyncOperation,mainThreadOperation);
    }

    private Callback<String> getMainThreadOperationForIpAddress() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                 adresaIp = AdresaIpJsonParser.fromJson(result);
                //Toast.makeText(getApplicationContext(),getString(R.string.tara_in_care_va_aflati) + adresaIp.getCountry() + getString(R.string.conform_adresei),Toast.LENGTH_LONG).show();


                //Context context = getApplicationContext();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle(getString(R.string.ati_fost_localizat));
                if(!adresaIp.getCity().equals("null"))
                    builder1.setMessage(getString(R.string.tara_in_care_va_aflati) + adresaIp.getCountry() + getString(R.string.oras) + adresaIp.getCity());
                else
                    builder1.setMessage(getString(R.string.tara_in_care_va_aflati) + adresaIp.getCountry());
                builder1.setCancelable(true);
                builder1.setIcon(android.R.drawable.ic_dialog_alert);

                builder1.setPositiveButton(
                        R.string.continuati,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                //aici adaug vizibilitatea butoanelor
                                gridLayout.setVisibility(View.VISIBLE);
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        };
    }
}