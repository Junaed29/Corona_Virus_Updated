package com.jsc.coronavirusdetails.ViewModel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jsc.coronavirusdetails.LocalDatabase.CoronaLDB_Details;
import com.jsc.coronavirusdetails.Repository.ApiRepository;
import com.jsc.coronavirusdetails.Repository.CoronaRepository;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CoronaViewModel extends AndroidViewModel {


    CoronaRepository coronaRepository;
    ApiRepository apiRepository;

    private LiveData<List<CoronaLDB_Details>> allCoronaDetailsData;
    private LiveData<CoronaLDB_Details> worldCoronaDetailsData;

    Application application;

    public CoronaViewModel(@NonNull Application application) {
        super(application);

        this.application = application;
        apiRepository = new ApiRepository(application);

        coronaRepository = new CoronaRepository(application);

        allCoronaDetailsData = coronaRepository.getAllCoronaDetailsData();
        worldCoronaDetailsData = coronaRepository.getWorldCoronaDetailsData();
    }

    public void setCoronaDetails(){
        apiRepository.insert();
    }
    public void updateCoronaDetails(){
        apiRepository.update();
    }

    public LiveData<List<CoronaLDB_Details>> getAllCoronaDetails() {
        return allCoronaDetailsData;
    }


    public LiveData<CoronaLDB_Details> getWorldCoronaDetails() {
        return worldCoronaDetailsData;
    }
}
