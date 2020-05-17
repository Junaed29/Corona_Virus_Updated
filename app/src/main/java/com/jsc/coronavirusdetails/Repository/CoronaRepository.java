package com.jsc.coronavirusdetails.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.jsc.coronavirusdetails.LocalDatabase.CoronaDetailsDao;
import com.jsc.coronavirusdetails.LocalDatabase.CoronaLDB_Details;
import com.jsc.coronavirusdetails.LocalDatabase.DetailsDatabase;

import java.util.List;

public class CoronaRepository {

    private CoronaDetailsDao detailsDao;
    private LiveData<List<CoronaLDB_Details>> allCoronaDetailsData;
    private LiveData<CoronaLDB_Details> worldCoronaDetailsData;


    public CoronaRepository(Application application) {
        DetailsDatabase database = DetailsDatabase.getDatabase(application);

        detailsDao = database.coronaDetailsDao();

        allCoronaDetailsData = detailsDao.getAllDetails();
        worldCoronaDetailsData = detailsDao.getWorldCoronaDetails();

    }


    public LiveData<List<CoronaLDB_Details>> getAllCoronaDetailsData() {
        return allCoronaDetailsData;
    }


    public LiveData<CoronaLDB_Details> getWorldCoronaDetailsData() {
        return worldCoronaDetailsData;
    }


    public void insert(List<CoronaLDB_Details> details) {
        new InsertTask(detailsDao).execute(details);
    }


    private class InsertTask extends AsyncTask<List<CoronaLDB_Details>, Void, Void> {
        private CoronaDetailsDao detailsDao;

        InsertTask(CoronaDetailsDao detailsDao) {
            this.detailsDao = detailsDao;
        }

        @Override
        protected Void doInBackground(List<CoronaLDB_Details>... lists) {
            detailsDao.insertAllDetails(lists[0]);
            return null;
        }
    }


    public void update(List<CoronaLDB_Details> details) {
        new UpdateTask(detailsDao).execute(details);
    }


    private class UpdateTask extends AsyncTask<List<CoronaLDB_Details>, Void, Void> {
        private CoronaDetailsDao detailsDao;

        UpdateTask(CoronaDetailsDao detailsDao) {
            this.detailsDao = detailsDao;
        }

        @Override
        protected Void doInBackground(List<CoronaLDB_Details>... lists) {
            detailsDao.deleteAll();
            detailsDao.insertAllDetails(lists[0]);
            return null;
        }
    }


}
