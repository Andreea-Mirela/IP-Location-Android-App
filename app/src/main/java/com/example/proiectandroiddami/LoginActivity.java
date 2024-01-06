package com.example.proiectandroiddami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public static final String PROFILE_SHARED_PREF = "profileSharedPref";
    public static final String CHECKED = "checked";
    public static final String EMAIL = "email";
    public static final String PAROLA = "parola";
    private EditText etEmail;
    private EditText etParola;
    private Button btnConectare;
    private TextView tvInregistrare;
    private ProgressBar pbInregistrare;
    private TextView tvResetare;

    private FirebaseAuth firebaseAuth;

    private CheckBox cbLogin;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializareComponente();

        btnConectare.setOnClickListener(evenimentClickConectare());

        tvInregistrare.setOnClickListener(evenimentClickTextViewInregistrare());

        tvResetare.setOnClickListener(evenimentTextViewResetareParola());

        sharedPreferences();
    }

    private void sharedPreferences() {
        Boolean checked=preferences.getBoolean(CHECKED,false);

        if(checked){
            etEmail.setText(preferences.getString(EMAIL,""));
            etParola.setText(preferences.getString(PAROLA,""));
        }
        cbLogin.setChecked(checked);
    }


    //region Eveniment Click TextView Resetare Parola
    private View.OnClickListener evenimentTextViewResetareParola() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText mailResetareParola = new EditText(v.getContext());
                final AlertDialog.Builder alertaResetare = new AlertDialog.Builder(v.getContext());
                alertaResetare.setTitle(R.string.resetare_parola_titlu);
                alertaResetare.setMessage(R.string.adresa_mail_resetare);
                alertaResetare.setView(mailResetareParola);

                Log.i("ajunge","ajunge aici");

                alertaResetare.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extrag adresa si link-ul de resetare

                        String mail = mailResetareParola.getText().toString();
                        Log.i("ajunge","ajunge aici");
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),getString(R.string.link_trimis),Toast.LENGTH_LONG).show();
                                Log.i("ajunge","ajunge aici");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),getString(R.string.link_resetare_netrimis) + e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

                alertaResetare.setNegativeButton("Nu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //inchid alerta
                        alertaResetare.setCancelable(true);
                    }
                });
                alertaResetare.create().show();
            }
        };
    }
    //endregion

    //region Eveniment Click TextView Inregistrare
    private View.OnClickListener evenimentClickTextViewInregistrare() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        };
    }
    //endregion

    //region Eveniment Click Conectare
    private View.OnClickListener evenimentClickConectare() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validare()) {
                    pbInregistrare.setVisibility(View.VISIBLE);

                    //inregistrare utilizator in firebase
                    String email = etEmail.getText().toString().trim();
                    String password = etParola.getText().toString().trim();

                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), R.string.conectare_cu_succes,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), getString(R.string.eroare) + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                pbInregistrare.setVisibility(View.GONE);
                            }
                        }
                    });

                    Boolean checked = cbLogin.isChecked();
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean(CHECKED,checked);
                    editor.putString(EMAIL,email);
                    editor.putString(PAROLA,password);
                    editor.apply();

                }
            }
        };
    }

    //region Validare Formular Inregistrare
    private boolean validare() {

        if(etEmail.getText() == null || etEmail.getText().toString().trim().length() < 6)
        {
            //Toast.makeText(getApplicationContext(), R.string.validare_email, Toast.LENGTH_LONG).show();
            etEmail.setError(getString(R.string.validare_email));
            return false;
        }
        if(etParola.getText() == null || etParola.getText().toString().trim().length() < 6)
        {
            //Toast.makeText(getApplicationContext(), R.string.validare_parola, Toast.LENGTH_LONG).show();
            etParola.setError(getString(R.string.validare_parola));
            return false;
        }
        return true;
    }
    //endregion

    //endregion

    //region Initializare Componente
    private void initializareComponente() {
        etEmail = findViewById(R.id.et_login_email);
        etParola = findViewById(R.id.et_login_parola);
        btnConectare = findViewById(R.id.btn_login_conectare);
        pbInregistrare = findViewById(R.id.pb_login_inregistrare);
        tvInregistrare = findViewById(R.id.tv_login_inregistrare);
        tvResetare = findViewById(R.id.tv_login_resetare_parola);

        firebaseAuth = FirebaseAuth.getInstance();

        cbLogin = findViewById(R.id.cb_login);
        preferences = getSharedPreferences(PROFILE_SHARED_PREF, MODE_PRIVATE);

        findViewById(R.id.activity_login).setOnTouchListener(new View.OnTouchListener() {
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
    //endregion
}