package com.example.proiectandroiddami.claseJson;

import android.util.Log;

import com.example.proiectandroiddami.database.model.Tara;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContinentJsonParser {

    public static final String NUME = "numeContinent";
    public static final String SUPRAFATA_CONTINENT = "suprafataContinent";
    public static final String DENSITATE = "densitate";
    public static final String TARA = "tara";
    public static final String DENUMIRE_TARA = "denumireTara";
    public static final String SUPRAFATA_TARA = "suprafataTara";
    public static final String POPULATIE_TARA = "populatieTara";
    public static final String ORAS = "oras";
    public static final String DENUMIRE_ORAS = "denumireOras";
    public static final String RISC_EPIDEMIOLOGIC = "riscEpidemiologic";
    public static final String COD = "cod";

    public static List<Continent> fromJson(String json)
    {
        if(json == null || json.isEmpty()) {
            Log.i("tag1" ,"Am ajuns in for");
            return new ArrayList<>();
        }
        try {
            JSONArray array = new JSONArray(json);
            return preiaContinenteDinJson(array);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Continent> preiaContinenteDinJson(JSONArray array) throws JSONException {
        List<Continent> listaContinente = new ArrayList<>();

        for(int i = 0; i < array.length(); i++) {

            JSONObject objectContinent = array.getJSONObject(i);

            String numeContinent = objectContinent.getString(NUME);
            String suprafataContinent = objectContinent.getString(SUPRAFATA_CONTINENT);
            String densitate = objectContinent.getString(DENSITATE);

            JSONObject objectTara = objectContinent.getJSONObject(TARA);

            String denumireTara = objectTara.getString(DENUMIRE_TARA);
            String suprafataTara = objectTara.getString(SUPRAFATA_TARA);
            String populatieTara = objectTara.getString(POPULATIE_TARA);

            JSONObject objectOras = objectTara.getJSONObject(ORAS);

            String denumireOras = objectOras.getString(DENUMIRE_ORAS);
            String riscEpidemiologic = objectOras.getString(RISC_EPIDEMIOLOGIC);
            String cod = objectOras.getString(COD);

            Oras oras = new Oras(denumireOras,riscEpidemiologic,cod);
            Tara tara = new Tara(denumireTara,suprafataTara,populatieTara,oras);
            Continent continent = new Continent(numeContinent, suprafataContinent, densitate, tara);

            listaContinente.add(continent);
        }
        return listaContinente;
    }
}
