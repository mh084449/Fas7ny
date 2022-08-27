package com.example.fasa7ni;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fasa7ni.database.Place;
import com.example.fasa7ni.database.Repo;

import java.util.List;

public class PlaceViewModel  extends AndroidViewModel {
    Repo repo;
    LiveData<List<Place>> allPlaces;
    public PlaceViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        allPlaces = repo.getPlaces();
    }

    public void insertPlace(Place place){
        repo.insertPlace(place);
    }
    public void deletePlace(Place place){
        repo.deletePlace(place);
    }

    public LiveData<List<Place>> getAllPlaces(){
        return allPlaces;
    }

}

