package com.jsc.coronavirusdetails.Repository;

import android.app.Application;

import com.jsc.coronavirusdetails.LocalDatabase.CoronaLDB_Details;
import com.jsc.coronavirusdetails.RestApi.ApiClient;
import com.jsc.coronavirusdetails.RestApi.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRepository {
    private static final String TAG = "ApiRepository";
    Retrofit retrofit;
    ApiInterface api;
    Application application;
    CoronaRepository coronaRepository;

    public ApiRepository(Application application) {
        this.application = application;
        coronaRepository = new CoronaRepository(application);
        retrofit = ApiClient.getClient();
        api = retrofit.create(ApiInterface.class);
    }

    public void insert() {
        Call<List<CoronaLDB_Details>> listCall = api.getAllCoronaDetails();

        listCall.enqueue(new Callback<List<CoronaLDB_Details>>() {
            @Override
            public void onResponse(Call<List<CoronaLDB_Details>> call, Response<List<CoronaLDB_Details>> response) {
                List<CoronaLDB_Details> detailsList = response.body();
                coronaRepository.insert(detailsList);
            }

            @Override
            public void onFailure(Call<List<CoronaLDB_Details>> call, Throwable t) {

            }
        });
    }


    public void update() {
        Call<List<CoronaLDB_Details>> listCall = api.getAllCoronaDetails();

        listCall.enqueue(new Callback<List<CoronaLDB_Details>>() {
            @Override
            public void onResponse(Call<List<CoronaLDB_Details>> call, Response<List<CoronaLDB_Details>> response) {
                List<CoronaLDB_Details> detailsList = response.body();
                coronaRepository.update(detailsList);
            }

            @Override
            public void onFailure(Call<List<CoronaLDB_Details>> call, Throwable t) {

            }
        });
    }
}
