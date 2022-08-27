package com.example.fasa7ni;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fasa7ni.database.Place;
import com.example.fasa7ni.databinding.PlaceItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PlaceViewHolder> {

    private List<Place> places = new ArrayList<>();
    PlaceActions placeActions;

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PlaceItemBinding placeItemBinding = PlaceItemBinding.inflate(layoutInflater,parent,false);

        return new PlaceViewHolder(placeItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        holder.placeItemBinding.setPlace(place);
        if(place.getUriImage() != null)
            holder.placeItemBinding.image.setImageURI(place.getUriImage());
        else
            holder.placeItemBinding.image.setImageResource(place.getDrawImage());
        holder.placeItemBinding.executePendingBindings();
        holder.bind(position);
    }

    public void setPlaceActions(PlaceActions placeActions) {
        this.placeActions = placeActions;
    }

    @Override
    public int getItemCount() {
        return  places.size();
    }

    public void setPlaces(List<Place> places){
        this.places = places;
        notifyDataSetChanged();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder{
        PlaceItemBinding placeItemBinding;


        public PlaceViewHolder(@NonNull PlaceItemBinding itemView) {
            super(itemView.getRoot());
            placeItemBinding = itemView;
        }

        public void bind(int pos){
            placeItemBinding.card.setOnClickListener(view -> placeActions.onItemClick(places.get(pos)));
        }
    }
}

