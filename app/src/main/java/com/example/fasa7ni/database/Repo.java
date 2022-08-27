package com.example.fasa7ni.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repo {
    private PlaceDB placeDB;
    private PlaceDao placeDao;
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<Place>> allOrderedPlaces;

    public Repo(Application application) {
        placeDB = PlaceDB.getInstance(application);
        placeDao = placeDB.placeDao();
        allPlaces = placeDao.getAllPlaces();
        allOrderedPlaces = placeDao.getOrderedPlaces();
    }

    public void insertPlace(Place place){
        new AsyncTaskExcutioner(placeDao,"I").execute(place);
    }
    public void deletePlace(Place place){
        new AsyncTaskExcutioner(placeDao,"D").execute(place);
    }
    public LiveData<List<Place>> getPlaces(){return allPlaces;}
    public LiveData<List<Place>> getOrderedPlaces(){return allOrderedPlaces;}



    public static class AsyncTaskExcutioner extends AsyncTask<Place,Void,Void>{
        private PlaceDao placeDao;
        String TOP;
        public AsyncTaskExcutioner(PlaceDao placeDao, String TOP) {
            this.placeDao = placeDao;
            this.TOP = TOP;
        }

        @Override
        protected Void doInBackground(Place... places) {
            if(TOP == "I") placeDao.insertPlace(places[0]);
            else if(TOP == "D") placeDao.deletePlace(places[0]);
            return null;
        }
    }
}

