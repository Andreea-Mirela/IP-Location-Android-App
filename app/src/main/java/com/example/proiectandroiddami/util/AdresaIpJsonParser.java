package com.example.proiectandroiddami.util;

import com.example.proiectandroiddami.database.model.AdresaIp;

import org.json.JSONException;
import org.json.JSONObject;

public class AdresaIpJsonParser {

    public static final String CONTINENT = "continent";
    public static final String COUNTRY = "country";
    public static final String REGION = "region";
    public static final String CITY = "city";
    public static final String CURRENCY = "currency";
    public static final String IP_ADDRESS = "ip_address";
    public static final String COUNTRY_CODE = "country_code";

    public static AdresaIp fromJson(String json) {
        if(json == null || json.isEmpty()) {
            return new AdresaIp();
        }

        try {
            JSONObject object = new JSONObject(json);

            //citesc atribut cu atribut
            String ip_address = object.getString(IP_ADDRESS);
            String country = object.getString(COUNTRY);
            String country_code = object.getString(COUNTRY_CODE);
            String continent = object.getString(CONTINENT);
            String city = object.getString(CITY);
            String region = object.getString(REGION);
            String regionName = object.getString(CURRENCY);

            //imi creez obiectul de tip AdresaIp
            AdresaIp adresaIp = new AdresaIp(ip_address,country,country_code,continent,city,region,regionName);

            //returnez obiectul creat
            return adresaIp;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //in caz ca se intampla ceva returnez un obiect de tip AdresaIp nul
        return new AdresaIp();
    }
}
