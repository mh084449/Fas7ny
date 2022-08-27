package com.example.fasa7ni.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlaceDao{

    @Insert
    void insertPlace(Place place);

    @Delete
    void deletePlace(Place place);

    @Query("SELECT * FROM places")
    LiveData<List<Place>> getAllPlaces();

    @Query("SELECT * FROM places ORDER BY nearbyDistance ASC")
    LiveData<List<Place>> getOrderedPlaces();


}

