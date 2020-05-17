package com.jsc.coronavirusdetails.RestApi;

import com.jsc.coronavirusdetails.LocalDatabase.CoronaLDB_Details;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("countries")
    Call<List<CoronaLDB_Details>> getAllCoronaDetails();
}
