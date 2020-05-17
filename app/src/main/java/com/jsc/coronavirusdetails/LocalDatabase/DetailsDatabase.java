package com.jsc.coronavirusdetails.LocalDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jsc.coronavirusdetails.RestApi.ApiClient;
import com.jsc.coronavirusdetails.RestApi.ApiInterface;
import com.jsc.coronavirusdetails.RestApi.CoronaPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


@Database(entities = {CoronaLDB_Details.class}, version = 1, exportSchema = false)
public abstract class DetailsDatabase extends RoomDatabase {



    private static volatile DetailsDatabase INSTANCE = null;

    public abstract CoronaDetailsDao coronaDetailsDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    public static DetailsDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (DetailsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DetailsDatabase.class, "corona_details_database")
                            /*******"fallbackToDestructiveMigration()" is used when we update our version it will destroy old entities*******/
                            .fallbackToDestructiveMigration()
                            /*******"addCallback(callback) is optional"*******/
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//
//            // If you want to keep data through app restarts,
//            // comment out the following block
//            DetailsDatabase.databaseWriteExecutor.execute(() -> {
//                CoronaDetailsDao detailsDao = INSTANCE.coronaDetailsDao();
//
//                Retrofit retrofit = ApiClient.getClient();
//
//                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//
//                Call<List<CoronaPojo>> detailsCall = apiInterface.getAllCoronaDetails();
//                detailsCall.enqueue(new retrofit2.Callback<List<CoronaPojo>>() {
//                    boolean flag = false;
//
//                    @Override
//                    public void onResponse(Call<List<CoronaPojo>> call, Response<List<CoronaPojo>> response) {
//                        List<CoronaPojo> getAllDetailsList = response.body();
//
//                        List<CoronaLDB_Details> details = new ArrayList<>();
//
//                        for (CoronaPojo pojo : getAllDetailsList) {
//                            int testsPerOneMillion = pojo.getTestsPerOneMillion();
//                            int totalTests = pojo.getTotalTests();
//                            int deathsPerOneMillion = pojo.getDeathsPerOneMillion();
//                            int casesPerOneMillion = pojo.getCasesPerOneMillion();
//                            int critical = pojo.getCritical();
//                            int active = pojo.getActive();
//                            int recovered = pojo.getRecovered();
//                            int todayDeaths = pojo.getTodayDeaths();
//                            int deaths = pojo.getDeaths();
//                            int todayCases = pojo.getTodayCases();
//                            int cases = pojo.getCases();
//                            String country = pojo.getCountry();
//
//                            CoronaLDB_Details coronaLDB_details = new CoronaLDB_Details(
//                                    testsPerOneMillion,
//                                    totalTests,
//                                    deathsPerOneMillion,
//                                    casesPerOneMillion,
//                                    critical,
//                                    active,
//                                    recovered,
//                                    todayDeaths,
//                                    deaths,
//                                    todayCases,
//                                    cases,
//                                    country
//                            );
//
//                            details.add(coronaLDB_details);
//                            detailsDao.insertAllDetails(details);
//                            flag = true;
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<List<CoronaPojo>> call, Throwable t) {
//                        flag = false;
//                    }
//
//                });
//            });
//        }
//    };

}
