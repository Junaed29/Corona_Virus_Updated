package com.jsc.coronavirusdetails.RestApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoronaPojo {
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
    private String country;

    public int getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(int testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public CoronaPojo() {
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public int getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(int deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(int casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
