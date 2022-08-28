package com.example.fasa7ni.database;


import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fasa7ni.R;

@Database(entities = {Place.class}, version = 1, exportSchema = false)
public abstract class PlaceDB extends RoomDatabase {

    private static PlaceDB instance = null;
    public abstract PlaceDao placeDao();

    public static synchronized PlaceDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),PlaceDB.class, "place_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new KickStart(instance).execute();
        }
    };

    private static class KickStart extends AsyncTask<Void,Void,Void> {
        private PlaceDao placeDao;
        public KickStart(PlaceDB placeDB) {
            placeDao = placeDB.placeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

//            Place place = new Place("Eiffel Tower","Land Mark","kkk",48.8584,2.2945,4);
//            place.setDrawImage(R.drawable.u1nknown);
//            placeDao.insertPlace(place);

            return null;
        }
    }
}