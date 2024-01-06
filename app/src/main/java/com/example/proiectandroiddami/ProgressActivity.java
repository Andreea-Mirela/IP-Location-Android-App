package com.example.proiectandroiddami;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {

    TextView textViewScor;
    Button btnInchide;
    ImageView viewTrofeu;
 //   GridLayout gridLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        textViewScor = findViewById(R.id.progress_tv_scor);
        btnInchide = findViewById(R.id.progress_btn_revenire);
        viewTrofeu = findViewById(R.id.progress_iv);
   //     gridLayout = findViewById(R.id.mida_grid);

//        Path path = new Path();
//        path.arcTo(-10f, 0f, 100f, 100f, 185f, -360f, true);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(viewTrofeu, View.X, View.Y, path);
//        animator.setDuration(10000);
//        animator.start();

        int nrRaspunsuriCorecte = getIntent().getIntExtra("raspunsuri_corecte", 0);

        textViewScor.setText(getString(R.string.scor).toString() + " " +nrRaspunsuriCorecte +" "+ getString(R.string.din_5).toString());

        btnInchide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                finish();
//                gridLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}