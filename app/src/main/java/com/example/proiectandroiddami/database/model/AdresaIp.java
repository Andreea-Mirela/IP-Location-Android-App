package com.example.proiectandroiddami.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "adreseIp" )
public class AdresaIp implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idAdresaIp")
    private long idAdresaIp;

    @ForeignKey(
            entity = Tara.class,
            parentColumns = "idTara",
            childColumns = "idFkTara",
            onDelete = CASCADE
    )
    @ColumnInfo(name = "idFkTara")
    private long idFkTara;


    @ColumnInfo(name = "ip_address")
    private String ip_address;

    @Ignore
    private String country;
    @Ignore
    private String country_code;
    @Ignore
    private String continent;

    @ColumnInfo(name = "city")
    private String city;
    @Ignore
    private String region;
    @Ignore
    private String currency;

    public AdresaIp(long idAdresaIp, long idFkTara, String ip_address, String city) {
        this.idAdresaIp = idAdresaIp;
        this.idFkTara = idFkTara;
        this.ip_address = ip_address;
        this.city = city;
    }

    @Ignore
    public AdresaIp(String ip_address, String city) {
        this.ip_address = ip_address;
        this.city = city;
    }

    @Ignore
    public AdresaIp() {
    }

    @Ignore
    public AdresaIp(String ip_address, String country, String country_code, String continent, String city, String region, String currency) {
        this.ip_address = ip_address;
        this.country = country;
        this.country_code = country_code;
        this.continent = continent;
        this.city = city;
        this.region = region;
        this.currency = currency;
    }

    public long getIdFkTara() {
        return idFkTara;
    }

    public void setIdFkTara(long idFkTara) {
        this.idFkTara = idFkTara;
    }

    public long getIdAdresaIp() {
        return idAdresaIp;
    }

    public void setIdAdresaIp(long idAdresaIp) {
        this.idAdresaIp = idAdresaIp;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        if(country==null && country_code==null && continent==null && region==null && currency==null){
            return "AdresaIp{" +
                    "ip_address='" + ip_address + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
        else
        return "AdresaIp{" +
                "ip_address='" + ip_address + '\'' +
                ", country='" + country + '\'' +
                ", country_code='" + country_code + '\'' +
                ", continent='" + continent + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
