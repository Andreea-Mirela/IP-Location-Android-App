package com.example.proiectandroiddami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizzActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Intrebare> listaIntrebari = new ArrayList<Intrebare>();

    ProgressBar progressBar;
    TextView textViewProgressBar;
    TextView textViewIntrebare;
    ImageView imageView;
    TextView textViewVarianta1;
    TextView textViewVarianta2;
    TextView textViewVarianta3;
    Button btnTrimite;

    private Integer pozitieIntrebareCurenta = 1;
    private Integer pozitieVariantaSelectata = 0;
    private Integer nrRaspunsuriCorecte = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

                Integer cod_quizz = (Integer) getIntent().getSerializableExtra("cod");
        if(cod_quizz!=null) {
            if (cod_quizz == 0)
                initializareIntrebariEuropa();
            else if (cod_quizz == 1)
                initializareIntrebariAfrica();
            else if (cod_quizz == 2)
                initializareIntrebariAmericaNord();
            else if (cod_quizz == 3)
                initializareIntrebariAmericaSud();
            else if (cod_quizz == 4)
                initializareIntrebariAsia();
            else if (cod_quizz == 5)
                initializareIntrebariAustraliaOceania();
        }
        else
            initializareIntrebariAfrica();

        initializareComponente();
        seteazaIntrebarea();

        textViewVarianta1.setOnClickListener(this);
        textViewVarianta2.setOnClickListener(this);
        textViewVarianta3.setOnClickListener(this);
        btnTrimite.setOnClickListener(this);
    }

    //region Initializare Intrebari Australia/Oceania
    private void initializareIntrebariAustraliaOceania() {
        Intrebare intrebarea1 = new Intrebare(1,
                getString(R.string.australia1),
                R.drawable.intaustralia1,
                getString(R.string.raspunsAustralia11),
                getString(R.string.raspunsAustralia12),
                getString(R.string.raspunsAustralia13),
                1
        );
        listaIntrebari.add(intrebarea1);

        Intrebare intrebarea2 = new Intrebare(2,
                getString(R.string.australia2),
                R.drawable.intaustralia2,
                getString(R.string.raspunsAustralia21),
                getString(R.string.raspunsAustralia22),
                getString(R.string.raspunsAustralia23),
                2
        );
        listaIntrebari.add(intrebarea2);

        Intrebare intrebarea3 = new Intrebare(3,
                getString(R.string.australia3),
                R.drawable.intaustralia3,
                getString(R.string.raspunsAustralia31),
                getString(R.string.raspunsAustralia32),
                getString(R.string.raspunsAustralia33),
                1
        );
        listaIntrebari.add(intrebarea3);

        Intrebare intrebarea4 = new Intrebare(4,
                getString(R.string.australia4),
                R.drawable.intaustralia4,
                getString(R.string.raspunsAustralia41),
                getString(R.string.raspunsAustralia42),
                getString(R.string.raspunsAustralia43),
                3
        );
        listaIntrebari.add(intrebarea4);
        Intrebare intrebarea5 = new Intrebare(5,
                getString(R.string.australia5),
                R.drawable.intaustralia5,
                getString(R.string.raspunsAustralia51),
                getString(R.string.raspunsAustralia52),
                getString(R.string.raspunsAustralia53),
                3
        );
        listaIntrebari.add(intrebarea5);
    }
    //endregion

    //region Initializare Intrebari Asia
    private void initializareIntrebariAsia() {
        Intrebare intrebarea1 = new Intrebare(1,
                getString(R.string.asia1),
                R.drawable.intasia1,
                getString(R.string.raspunsAsia11),
                getString(R.string.raspunsAsia12),
                getString(R.string.raspunsAsia13),
                1
        );
        listaIntrebari.add(intrebarea1);

        Intrebare intrebarea2 = new Intrebare(2,
                getString(R.string.asia2),
                R.drawable.intasia2,
                getString(R.string.raspunsAsia21),
                getString(R.string.raspunsAsia22),
                getString(R.string.raspunsAsia23),
                2
        );
        listaIntrebari.add(intrebarea2);

        Intrebare intrebarea3 = new Intrebare(3,
                getString(R.string.asia3),
                R.drawable.intasia3,
                getString(R.string.raspunsAsia31),
                getString(R.string.raspunsAsia32),
                getString(R.string.raspunsAsia33),
                1
        );
        listaIntrebari.add(intrebarea3);

        Intrebare intrebarea4 = new Intrebare(4,
                getString(R.string.asia4),
                R.drawable.intasia4,
                getString(R.string.raspunsAsia41),
                getString(R.string.raspunsAsia42),
                getString(R.string.raspunsAsia43),
                3
        );
        listaIntrebari.add(intrebarea4);
        Intrebare intrebarea5 = new Intrebare(5,
                getString(R.string.asia5),
                R.drawable.intasia5,
                getString(R.string.raspunsAsia51),
                getString(R.string.raspunsAsia52),
                getString(R.string.raspunsAsia53),
                3
        );
        listaIntrebari.add(intrebarea5);
    }
    //endregion

    //region Initializare Intrebari America De Sud
    private void initializareIntrebariAmericaSud() {
        Intrebare intrebarea1 = new Intrebare(1,
                getString(R.string.americasud1),
                R.drawable.intamericasud1,
                getString(R.string.raspunsAmericaSud11),
                getString(R.string.raspunsAmericaSud12),
                getString(R.string.raspunsAmericaSud13),
                1
        );
        listaIntrebari.add(intrebarea1);

        Intrebare intrebarea2 = new Intrebare(2,
                getString(R.string.americasud2),
                R.drawable.intamericasud2,
                getString(R.string.raspunsAmericaSud21),
                getString(R.string.raspunsAmericaSud22),
                getString(R.string.raspunsAmericaSud23),
                2
        );
        listaIntrebari.add(intrebarea2);

        Intrebare intrebarea3 = new Intrebare(3,
                getString(R.string.americasud3),
                R.drawable.intamericasud3,
                getString(R.string.raspunsAmericaSud31),
                getString(R.string.raspunsAmericaSud32),
                getString(R.string.raspunsAmericaSud33),
                1
        );
        listaIntrebari.add(intrebarea3);

        Intrebare intrebarea4 = new Intrebare(4,
                getString(R.string.americasud4),
                R.drawable.intamericasud4,
                getString(R.string.raspunsAmericaSud41),
                getString(R.string.raspunsAmericaSud42),
                getString(R.string.raspunsAmericaSud43),
                3
        );
        listaIntrebari.add(intrebarea4);
        Intrebare intrebarea5 = new Intrebare(5,
                getString(R.string.americasud5),
                R.drawable.intamericasud5,
                getString(R.string.raspunsAmericaSud51),
                getString(R.string.raspunsAmericaSud52),
                getString(R.string.raspunsAmericaSud53),
                3
        );
        listaIntrebari.add(intrebarea5);
    }
    //endregion

    //region Initializare Intrebari America De Nord
    private void initializareIntrebariAmericaNord() {
        Intrebare intrebarea1 = new Intrebare(1,
                getString(R.string.americanord1),
                R.drawable.intamericanord1,
                getString(R.string.raspunsAmericaNord11),
                getString(R.string.raspunsAmericaNord12),
                getString(R.string.raspunsAmericaNord13),
                1
        );
        listaIntrebari.add(intrebarea1);

        Intrebare intrebarea2 = new Intrebare(2,
                getString(R.string.americanord2),
                R.drawable.intamericanord2,
                getString(R.string.raspunsAmericaNord21),
                getString(R.string.raspunsAmericaNord22),
                getString(R.string.raspunsAmericaNord23),
                2
        );
        listaIntrebari.add(intrebarea2);

        Intrebare intrebarea3 = new Intrebare(3,
                getString(R.string.americanord3),
                R.drawable.intamericanord3,
                getString(R.string.raspunsAmericaNord31),
                getString(R.string.raspunsAmericaNord32),
                getString(R.string.raspunsAmericaNord33),
                1
        );
        listaIntrebari.add(intrebarea3);

        Intrebare intrebarea4 = new Intrebare(4,
                getString(R.string.americanord4),
                R.drawable.intamericanord4,
                getString(R.string.raspunsAmericaNord41),
                getString(R.string.raspunsAmericaNord42),
                getString(R.string.raspunsAmericaNord43),
                3
        );
        listaIntrebari.add(intrebarea4);
        Intrebare intrebarea5 = new Intrebare(5,
                getString(R.string.americanord5),
                R.drawable.intamericanord5,
                getString(R.string.raspunsAmericaNord51),
                getString(R.string.raspunsAmericaNord52),
                getString(R.string.raspunsAmericaNord53),
                3
        );
        listaIntrebari.add(intrebarea5);
    }
    //endregion

    //region Initializare Intrebari Africa
    private void initializareIntrebariAfrica() {
        Intrebare intrebarea1 = new Intrebare(1,
                getString(R.string.africa1),
                R.drawable.intafrica1,
                getString(R.string.raspunsAfrica11),
                getString(R.string.raspunsAfrica12),
                getString(R.string.raspunsAfrica13),
                1
        );
        listaIntrebari.add(intrebarea1);

        Intrebare intrebarea2 = new Intrebare(2,
                getString(R.string.africa2),
                R.drawable.intafrica2,
                getString(R.string.raspunsAfrica21),
                getString(R.string.raspunsAfrica22),
                getString(R.string.raspunsAfrica23),
                2
        );
        listaIntrebari.add(intrebarea2);

        Intrebare intrebarea3 = new Intrebare(3,
                getString(R.string.africa3),
                R.drawable.intafrica3,
                getString(R.string.raspunsAfrica31),
                getString(R.string.raspunsAfrica32),
                getString(R.string.raspunsAfrica33),
                1
        );
        listaIntrebari.add(intrebarea3);

        Intrebare intrebarea4 = new Intrebare(4,
                getString(R.string.africa4),
                R.drawable.intafrica4,
                getString(R.string.raspunsAfrica41),
                getString(R.string.raspunsAfrica42),
                getString(R.string.raspunsAfrica43),
                3
        );
        listaIntrebari.add(intrebarea4);
        Intrebare intrebarea5 = new Intrebare(5,
                getString(R.string.africa5),
                R.drawable.intafrica5,
                getString(R.string.raspunsAfrica51),
                getString(R.string.raspunsAfrica52),
                getString(R.string.raspunsAfrica53),
                3
        );
        listaIntrebari.add(intrebarea5);
    }
    //endregion

    //region Initializare Intrebari Europa
    private void initializareIntrebariEuropa() {
        Intrebare intrebarea1 = new Intrebare(1,
                getString(R.string.europa1),
                R.drawable.int1,
                getString(R.string.raspunsEuropa11),
                getString(R.string.raspunsEuropa12),
                getString(R.string.raspunsEuropa13),
                1
        );
        listaIntrebari.add(intrebarea1);

        Intrebare intrebarea2 = new Intrebare(2,
                getString(R.string.europa2),
                R.drawable.int2,
                getString(R.string.raspunsEuropa21),
                getString(R.string.raspunsEuropa22),
                getString(R.string.raspunsEuropa23),
                2
        );
        listaIntrebari.add(intrebarea2);

        Intrebare intrebarea3 = new Intrebare(3,
                getString(R.string.europa3),
                R.drawable.int3,
                getString(R.string.raspunsEuropa31),
                getString(R.string.raspunsEuropa32),
                getString(R.string.raspunsEuropa33),
                1
        );
        listaIntrebari.add(intrebarea3);

        Intrebare intrebarea4 = new Intrebare(4,
                getString(R.string.europa4),
                R.drawable.int4,
                getString(R.string.raspunsEuropa41),
                getString(R.string.raspunsEuropa42),
                getString(R.string.raspunsEuropa43),
                3
        );
        listaIntrebari.add(intrebarea4);
        Intrebare intrebarea5 = new Intrebare(5,
                getString(R.string.europa5),
                R.drawable.int4,
                getString(R.string.raspunsEuropa51),
                getString(R.string.raspunsEuropa52),
                getString(R.string.raspunsEuropa53),
                3
        );
        listaIntrebari.add(intrebarea5);
    }
    //endregion

    //region Eveniment Click Controale Quizz
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quizz_tv_cat1:
                seteazaAspectRaspunsSelectat(textViewVarianta1,1);
                break;
            case R.id.quizz_tv_cat2:
                seteazaAspectRaspunsSelectat(textViewVarianta2,2);
                break;
            case R.id.quizz_tv_cat3:
                seteazaAspectRaspunsSelectat(textViewVarianta3,3);
                break;
            case R.id.quizz_btn_trimite:
                if(pozitieVariantaSelectata == 0){
                    pozitieIntrebareCurenta++;

                    if(pozitieIntrebareCurenta <= listaIntrebari.size())
                    {
                        seteazaIntrebarea();
                        textViewVarianta1.setEnabled(true);
                        textViewVarianta2.setEnabled(true);
                        textViewVarianta3.setEnabled(true);
                    } else {
                        Intent intent = new Intent(this,ProgressActivity.class);
                        intent.putExtra("raspunsuri_corecte",nrRaspunsuriCorecte);
                        startActivity(intent);
                        finish();
                        //Toast.makeText(this,"Ai terminat cu succes jocul", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intrebare intrebareCurenta = listaIntrebari.get(pozitieIntrebareCurenta-1);
                    if(!intrebareCurenta.idRaspunsCorect.equals(pozitieVariantaSelectata)){
                        afiseazaCorectitudineRaspuns(pozitieVariantaSelectata,R.drawable.custom_wrong_tv_cat);
                    } else {
                        nrRaspunsuriCorecte++;
                    }
                    afiseazaCorectitudineRaspuns(intrebareCurenta.idRaspunsCorect,R.drawable.custom_correct_tv_cat);
                    textViewVarianta1.setEnabled(false);
                    textViewVarianta2.setEnabled(false);
                    textViewVarianta3.setEnabled(false);

                    if(pozitieIntrebareCurenta == listaIntrebari.size()) {
                        btnTrimite.setText(R.string.finalizeaza);
                    } else {
                        btnTrimite.setText(R.string.urmatoarea_intrebare);

                    }

                    pozitieVariantaSelectata = 0;
                }
                break;
        }
    }

    void seteazaAspectRaspunsSelectat(TextView textView, Integer raspunsSelectat) {

        seteazaAspectDefaultRaspunsuri();

        pozitieVariantaSelectata = raspunsSelectat;
        textView.setTextColor(Color.parseColor("#00342d"));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_selected_tv_cat));

    }

    void afiseazaCorectitudineRaspuns(int raspuns, int drawabaleView)
    {
        switch (raspuns){
            case 1:
                textViewVarianta1.setBackground(ContextCompat.getDrawable(this,drawabaleView));
                break;
            case 2:
                textViewVarianta2.setBackground(ContextCompat.getDrawable(this,drawabaleView));
                break;
            case 3:
                textViewVarianta3.setBackground(ContextCompat.getDrawable(this,drawabaleView));
                break;
        }
    }
    //endregion

    //region Seteaza Intrebarea
    @SuppressLint("SetTextI18n")
    private void seteazaIntrebarea() {
        seteazaAspectDefaultRaspunsuri();

        if(pozitieIntrebareCurenta == listaIntrebari.size())
        {
            btnTrimite.setText(R.string.finalizeaza);
        } else {
            btnTrimite.setText(R.string.trimite);
        }

        Intrebare intrebareCurenta = listaIntrebari.get(pozitieIntrebareCurenta - 1);

        progressBar.setProgress(pozitieIntrebareCurenta);
        textViewProgressBar.setText(pozitieIntrebareCurenta + getString(R.string.bara) + progressBar.getMax());
        textViewIntrebare.setText(intrebareCurenta.text);
        imageView.setImageResource(intrebareCurenta.idImagine);
        textViewVarianta1.setText(intrebareCurenta.raspuns1);
        textViewVarianta2.setText(intrebareCurenta.raspuns2);
        textViewVarianta3.setText(intrebareCurenta.raspuns3);

    }
    void seteazaAspectDefaultRaspunsuri() {
        ArrayList<TextView> raspunsuri = new ArrayList<TextView>();
        raspunsuri.add(0, textViewVarianta1);
        raspunsuri.add(1, textViewVarianta2);
        raspunsuri.add(2, textViewVarianta3);
        for (TextView raspuns : raspunsuri) {
            raspuns.setTextColor(Color.parseColor("#1e4537"));
            raspuns.setTypeface(Typeface.DEFAULT);
            raspuns.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_tv_cat));
        }


    }
    //endregion

    //region Initializare Componente
    private void initializareComponente() {
        progressBar = findViewById(R.id.quizz_pb);
        textViewProgressBar = findViewById(R.id.quizz_tv_pb);
        textViewIntrebare = findViewById(R.id.quizz_tv_intrebare);
        imageView = findViewById(R.id.quizz_iv);
        textViewVarianta1 = findViewById(R.id.quizz_tv_cat1);
        textViewVarianta2 = findViewById(R.id.quizz_tv_cat2);
        textViewVarianta3 = findViewById(R.id.quizz_tv_cat3);
        btnTrimite=findViewById(R.id.quizz_btn_trimite);
    }

    //endregion
}