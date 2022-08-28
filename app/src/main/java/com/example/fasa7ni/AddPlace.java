package com.example.fasa7ni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.fasa7ni.database.Place;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddPlace extends AppCompatActivity {


    public static final String EXTRA_TITLE = "com.example.myfirstapp.TITLE";
    public static final String EXTRA_TYPE = "com.example.myfirstapp.TYPE";
    public static final String EXTRA_URI = "com.example.myfirstapp.URI";
    public static final String EXTRA_LONGIT = "com.example.myfirstapp.LONGIT";
    public static final String EXTRA_LAT = "com.example.myfirstapp.LAT";
    public static final String EXTRA_RATING = "com.example.myfirstapp.RATING";
    private static final String[] TYPES =  new String[]{};
    private EditText longit;
    private EditText lat;
    private AutoCompleteTextView type;
    private EditText title;
    private FloatingActionButton save;
    private Button add_photo;
    private ImageView imageView;
    private RatingBar ratingBar;
    Uri uri;
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
        add_photo = findViewById(R.id.add_photo);
        imageView = findViewById(R.id.picked_image);

        add_photo.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select image"),1);
        });


        save.setOnClickListener(view -> {
            toMainActivity();});

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == Activity.RESULT_OK && requestCode == 1){
            uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

    private void toMainActivity(){
        String Stitle = title.getText().toString();
        String Stype = type.getText().toString();
        double Dlongit = Double.parseDouble(longit.getText().toString());
        double Dlat = Double.parseDouble(lat.getText().toString());
        int rating = (int) ratingBar.getNumStars();
        String imageUriS = uri.toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_TITLE, Stitle);
        intent.putExtra(EXTRA_LAT,Dlat);
        intent.putExtra(EXTRA_LONGIT,Dlongit);
        intent.putExtra(EXTRA_URI,imageUriS);
        intent.putExtra(EXTRA_TYPE, Stype);
        intent.putExtra(EXTRA_TYPE, Stype);
        intent.putExtra(EXTRA_RATING, rating);
        if(Stitle == null)
            Toast.makeText(this, "please add a title", Toast.LENGTH_SHORT);
        else
            startActivity(intent);
    }


}