package com.jsc.coronavirusdetails.LocalDatabase;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "corona_details")
public class CoronaLDB_Details {

    @Expose
    @SerializedName("testsPerOneMillion")
    private int testsPerOneMillion;
    @Expose
    @SerializedName("totalTests")
    private int totalTests;
    @Expose
    @SerializedName("deathsPerOneMillion")
    private int deathsPerOneMillion;
    @Expose
    @SerializedName("casesPerOneMillion")
    private int casesPerOneMillion;
    @Expose
    @SerializedName("critical")
    private int critical;
    @Expose
    @SerializedName("active")
    private int active;
    @Expose
    @SerializedName("recovered")
    private int recovered;
    @Expose
    @SerializedName("todayDeaths")
    private int todayDeaths;
    @Expose
    @SerializedName("deaths")
    private int deaths;
    @Expose
    @SerializedName("todayCases")
    private int todayCases;
    @Expose
    @SerializedName("cases")
    private int cases;


    @Expose
    @SerializedName("country")
    @NonNull
    @PrimaryKey
    private String country;




    public CoronaLDB_Details(int testsPerOneMillion,
                             int totalTests,
                             int deathsPerOneMillion,
                             int casesPerOneMillion,
                             int critical,
                             int active,
                             int recovered,
                             int todayDeaths,
                             int deaths,
                             int todayCases,
                             int cases,
                             String country) {

        this.testsPerOneMillion = testsPerOneMillion;
        this.totalTests = totalTests;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.casesPerOneMillion = casesPerOneMillion;
        this.critical = critical;
        this.active = active;
        this.recovered = recovered;
        this.todayDeaths = todayDeaths;
        this.deaths = deaths;
        this.todayCases = todayCases;
        this.cases = cases;
        this.country = country;
    }


    public int getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public int getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public int getCritical() {
        return critical;
    }

    public int getActive() {
        return active;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getCases() {
        return cases;
    }

    @NotNull
    public String getCountry() {
        return country;
    }


}
