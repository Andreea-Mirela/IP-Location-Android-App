package com.example.proiectandroiddami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNume;
    private EditText etEmail;
    private EditText etParola;
    private EditText etTelefon;
    private Button btnInregistrare;
    private TextView tvConectare;
    private ProgressBar pbInregistrare;
    private FirebaseAuth firebaseAuth;
    private String userID;

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializareComponente();

        if(firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
            startActivity(intent);
            finish();
            //recomandare seminar sa nu facem asta dar cu ce pot inlocui?
        }

        btnInregistrare.setOnClickListener(metodaClickButonInregistrare());

        tvConectare.setOnClickListener(evenimentClickTextViewConectare());

    }

    //region Eveniment Click TextView Conectare
    private View.OnClickListener evenimentClickTextViewConectare() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        };
    }
    //endregion

    //region Eveniment Click Buton Inregistrare
    private View.OnClickListener metodaClickButonInregistrare() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inregistrare utilizator in firebase
                final String email = etEmail.getText().toString().trim();
                String password = etParola.getText().toString().trim();
                if(validare())
                {
                    pbInregistrare.setVisibility(View.VISIBLE);

                    final String nume = etNume.getText().toString();
                    final String telefon = etTelefon.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                                //trimit link de verificare
                                FirebaseUser fuser = firebaseAuth.getCurrentUser();
                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(),getString(R.string.link_verificare_trimis),Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("TAG",getString(R.string.link_verificare_netrimis) + e.getMessage());
                                    }
                                });



                                Toast.makeText(getApplicationContext(), R.string.utilizator_creat,Toast.LENGTH_LONG).show();

                                //pt salvarea informatiilor extra in baza de date FireBase
                                userID=firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("nume", nume);
                                user.put("email", email);
                                user.put("telefon", telefon);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.i("TAG",getString(R.string.log_onsucces) + userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("TAG",getString(R.string.onFailure)+e.toString());
                                    }
                                });

                                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), getString(R.string.eroare) + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                pbInregistrare.setVisibility(View.GONE);

                            }
                        }
                    });
                }
            }
        };
    }

    //region Validare Formular Inregistrare
    private boolean validare() {
        if(etNume.getText() == null || etNume.getText().toString().trim().length() < 6)
        {
            //Toast.makeText(getApplicationContext(), R.string.validare_nume, Toast.LENGTH_LONG).show();
            etNume.setError(getString(R.string.validare_nume));
            return false;
        }
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
        if(etTelefon.getText() == null)
        {
            //Toast.makeText(getApplicationContext(), R.string.validare_telefon, Toast.LENGTH_LONG).show();
            etTelefon.setError(getString(R.string.validare_telefon));
            return false;
        }
        return true;
    }
    //endregion

    //endregion

    //region Initializare Componente
    private void initializareComponente() {
        etNume=findViewById(R.id.et_nume);
        etEmail=findViewById(R.id.et_email);
        etParola=findViewById(R.id.et_parola);
        etTelefon=findViewById(R.id.et_telefon);
        btnInregistrare=findViewById(R.id.btn_inregistrare);
        tvConectare = findViewById(R.id.tv_conectare);
        pbInregistrare=findViewById(R.id.pb_conectare);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //ascund tastatura in afara EditText-urior (in formularul de inregistrare)
        findViewById(R.id.activity_register).setOnTouchListener(new View.OnTouchListener() {
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