package com.example.proiectandroiddami.claseJson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//import com.example.json_proiect.R;

import com.example.proiectandroiddami.R;

import java.util.List;

public class ContinentAdapter extends ArrayAdapter<Continent> {

    private Context context;
    private List<Continent> listaContinente;
    private LayoutInflater inflater;
    private int resource;
    public ContinentAdapter(@NonNull Context context, int resource, @NonNull List<Continent> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.listaContinente = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Continent continent = listaContinente.get(position);
        if(continent != null) {
            adaugaNumeContinent(view, continent.getNumeContinent());
            adaugaSuprafataContinent(view, continent.getSuprafataContinent());
            adaugaDensitate(view, continent.getDensitate());

            TextView textViewNumeTara = view.findViewById(R.id.tv_row_nume_tara);
            if(continent.getTara()!=null){
            textViewNumeTara.setText(continent.getTara().getDenumireTara());

            TextView textViewSuprafataTara = view.findViewById(R.id.tv_row_suprafata_tara);
            textViewSuprafataTara.setText(continent.getTara().getSuprafataTara());

            TextView textViewPopulatieTara = view.findViewById(R.id.tv_row_populatie);
            textViewPopulatieTara.setText(continent.getTara().getPopulatieTara());

            TextView textViewDenumireOras = view.findViewById(R.id.tv_row_denumire_oras);
            textViewDenumireOras.setText(continent.getTara().getOras().getDenumireOras());

            TextView textViewRiscEpidemiologic = view.findViewById(R.id.tv_row_risc_epidemiologic);
            textViewRiscEpidemiologic.setText("RISC EPIDEMIOLOGIC: "+continent.getTara().getOras().getRiscEpidemiologic());

            TextView textViewCod = view.findViewById(R.id.tv_row_cod);
            textViewCod.setText("COD ORAS: "+continent.getTara().getOras().getCod());}

        }
        return view;
    }

    private void adaugaNumeContinent(View view, String nume) {
        TextView textView = view.findViewById(R.id.tv_row_nume_continent);
        populareContinutTextView(nume, textView);
    }

    private void populareContinutTextView(String value, TextView textView) {
        if(value!=null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.nepreluat);
        }
    }
    private void adaugaSuprafataContinent(View view, String nume) {
        TextView textView = view.findViewById(R.id.tv_row_suprafata_continent);
        populareContinutTextView(nume, textView);
    }
    private void adaugaDensitate(View view, String rezultat) {
        TextView textView = view.findViewById(R.id.tv_row_densitate);
        populareContinutTextView(rezultat, textView);
    }
}
