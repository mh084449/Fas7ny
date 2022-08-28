package com.example.fasa7ni.database;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.fasa7ni.R;

@Entity(tableName = "places")
public class Place {
    @PrimaryKey
    @NonNull
    private String name;
    @NonNull
    private String type;
    private String description;
    private double longit;
    private double lat;
    private int nearbyDistance;
    private int drawImage = R.drawable.ic_baseline_image_24;
    public String UriImage = "null";

    public Place(@NonNull String name, @NonNull String type, String description,double longit,double lat, int nearbyDistance) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.longit = longit;
        this.lat = lat;
        this.nearbyDistance = nearbyDistance;
    }

    public double getLongit() {
        return longit;
    }

    public void setLongit(double longit) {
        this.longit = longit;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getNearbyDistance() {
        return nearbyDistance;
    }

    public void setNearbyDistance(int nearbyDistance) {
        this.nearbyDistance = nearbyDistance;
    }


    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDrawImage() {
        return drawImage;
    }

    public void setDrawImage(int drawImage) {
        this.drawImage = drawImage;
    }

    public Uri getUriImage() {
        if(UriImage == "null")
            return null;
        return Uri.parse(UriImage);
    }

    public void setUriImage(Uri uriImage) {

        UriImage = uriImage.toString();
    }
}
