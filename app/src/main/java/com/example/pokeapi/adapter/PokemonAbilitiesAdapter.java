package com.example.pokeapi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeapi.R;
import com.example.pokeapi.common.Common;
import com.robertlevonyan.views.chip.Chip;

import java.util.ArrayList;

public class PokemonAbilitiesAdapter extends RecyclerView.Adapter<PokemonAbilitiesAdapter.MyViewHolder>{
    Context context;
    ArrayList<String> typeList;

    public PokemonAbilitiesAdapter(Context context) {
        this.context = context;
        this.typeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PokemonAbilitiesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false);
        return new PokemonAbilitiesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAbilitiesAdapter.MyViewHolder holder, int position) {
        holder.chip.setText(typeList.get(position));
        holder.chip.setChipBackgroundColor(Color.parseColor(Common.getColorByType(typeList.get(position))));
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public void addTypes(ArrayList<String> types) {
        typeList.addAll(types);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip chip;
        public MyViewHolder(View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);
        }
    }
}
