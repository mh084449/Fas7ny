package com.example.fasa7ni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.fasa7ni.database.Place;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PlaceActions {

    private PlaceViewModel placeViewModel;
    private FloatingActionButton toAddPlaceButton;
    RecyclerViewAdapter recyclerViewAdapter;
    private Middler middler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setPlaceActions(this);

        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        placeViewModel.allPlaces.observe(this, places -> recyclerViewAdapter.setPlaces(places));

        toAddPlaceButton = findViewById(R.id.add_place_button);
        toAddPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToAddPlaceButton();
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra(AddPlace.EXTRA_MESSAGE);

        if(message == "message"){
            placeViewModel.insertPlace(middler.savPlace());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        placeViewModel.allPlaces.observe(this, places -> recyclerViewAdapter.setPlaces(places));
    }

    void setToAddPlaceButton(){
        Intent intent = new Intent(this, AddPlace.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(Place pos) {
        String s = "geo:"+pos.getLongit()+","+pos.getLat();
        Uri gmmIntentUri = Uri.parse(s);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}