package com.example.proiectandroiddami.claseJson;

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

public class OrasAdapter extends ArrayAdapter<Oras> {

    private Context context;
    private int resource;
    private List<Oras> orase;
    private LayoutInflater inflater;

    public OrasAdapter(@NonNull Context context,
                                int resource,
                                @NonNull List<Oras> objects,
                                LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.orase = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Oras oras = orase.get(position);
        if(oras != null) {
            adaugaDenumireOras(view, oras.getDenumireOras());
            adaugaRiscEpidemiologic(view, oras.getRiscEpidemiologic());
            adaugaCod(view, oras.getCod());
        }
        return view;
    }

    private void adaugaDenumireOras(View view, String denumire) {
        TextView textView = view.findViewById(R.id.tv_row_denumire_oras2);
        populareContinutTextView(denumire, textView);
    }

    private void populareContinutTextView(String value, TextView textView) {
        if(value!=null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.nepreluat);
        }
    }

    private void adaugaRiscEpidemiologic(View view, String risc) {
        TextView textView = view.findViewById(R.id.tv_row_risc_epidemiologic2);
        populareContinutTextView(risc, textView);
    }

    private void adaugaCod(View view, String cod) {
        TextView textView = view.findViewById(R.id.tv_cod2);
        populareContinutTextView(cod, textView);
    }

}
