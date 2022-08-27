package com.example.fasa7ni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.fasa7ni.database.Place;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddPlace extends AppCompatActivity implements Middler {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String[] TYPES =  new String[]{};
    private EditText longit;
    private EditText lat;
    private AutoCompleteTextView type;
    private EditText title;
    private FloatingActionButton save;
    private Button exit;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        longit = findViewById(R.id.longit_input);
        lat = findViewById(R.id.lat_input);
        type = findViewById(R.id.type_input);
        title = findViewById(R.id.title_input);
        ratingBar = findViewById(R.id.rating_input);
        save = findViewById(R.id.save_button);


        save.setOnClickListener(view -> toMainActivity());

    }
    private void toMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "message");
        startActivity(intent);
    }

    @Override
    public Place savPlace() {
        String Stitle = title.getText().toString();
        String Stype = type.getText().toString();
        double Dlongit = Double.parseDouble(longit.getText().toString());
        double Dlat = Double.parseDouble(lat.getText().toString());
        int rating = ratingBar.getNumStars();

        Place place = new Place(Stitle,Stype,Stitle,Dlongit,Dlat,rating);
        place.setDrawImage(R.drawable.ic_baseline_add_24);

        return place;
    }
}