package com.jsc.coronavirusdetails.LocalDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CoronaDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDetails(List<CoronaLDB_Details> details);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAllDetails(List<CoronaLDB_Details> details);


    @Query("SELECT * FROM CORONA_DETAILS ORDER BY cases DESC")
    LiveData<List<CoronaLDB_Details>> getAllDetails();

    @Query("SELECT * FROM CORONA_DETAILS WHERE country LIKE 'World'")
    LiveData<CoronaLDB_Details> getWorldCoronaDetails();


    @Query("DELETE FROM CORONA_DETAILS")
    void deleteAll();
}
