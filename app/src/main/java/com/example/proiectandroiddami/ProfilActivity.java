package com.example.proiectandroiddami;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfilActivity extends AppCompatActivity {

    TextView nume;
    TextView email;
    TextView telefon;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String idUtilizator;

    TextView tvEmailVerificat;
    Button btnVerificaMail;
    Button btnMeniuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        initializareComponente();

        final FirebaseUser user = firebaseAuth.getCurrentUser();
        if(!user.isEmailVerified()) {
            tvEmailVerificat.setVisibility(View.VISIBLE);
            btnVerificaMail.setVisibility(View.VISIBLE);

            btnVerificaMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
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
                }
            });

        }

        DocumentReference documentReference = firebaseFirestore.collection("users").document(idUtilizator);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                nume.setText(documentSnapshot.getString("nume"));
                email.setText(documentSnapshot.getString("email"));
                telefon.setText(documentSnapshot.getString("telefon"));
            }
        });


        btnMeniuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initializareComponente() {
        nume = findViewById(R.id.tv_profil_nume);
        email = findViewById(R.id.tv_profil_mail);
        telefon = findViewById(R.id.tv_profil_telefon);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        idUtilizator = firebaseAuth.getCurrentUser().getUid();

        tvEmailVerificat=findViewById(R.id.tv_email_verificat);
        btnVerificaMail=findViewById(R.id.btn_verifica_mail);

        btnMeniuPrincipal = findViewById(R.id.btn_meniu_principal);
    }

    public void deconectare(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}