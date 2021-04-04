package com.example.pokeapi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokeapi.R;
import com.example.pokeapi.adapter.PokemonAbilitiesAdapter;
import com.example.pokeapi.adapter.PokemonTypeAdapter;
import com.example.pokeapi.models.Ability;
import com.example.pokeapi.models.ListAbility;
import com.example.pokeapi.models.ListTypes;
import com.example.pokeapi.models.Pokemon;
import com.example.pokeapi.pokeapi.PokemonService2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailPokemon extends AppCompatActivity{

    private TextView weightTextView, heightTextView, nameTextView, baseExperienceTextView;
    private ImageView imagenImageView;
    private String id, name, url;
    private RecyclerView typeRecyclerView, habilitiesRecyclerView;
    private PokemonTypeAdapter typeAdapter;
    private PokemonAbilitiesAdapter abilitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pokemon);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            id = bundle.getInt("id")+"";
            name = bundle.getString("name");
            url = bundle.getString("url");
            this.setTitle(name.toUpperCase());
            getDetails();
            getAbilities();
            getTypes();
        }
        imagenImageView = findViewById(R.id.imagenImageView);
        weightTextView = findViewById(R.id.weightTextView);
        heightTextView = findViewById(R.id.heightTextView);
        nameTextView = findViewById(R.id.nameTextView);
        baseExperienceTextView = findViewById(R.id.baseExperienceTextView);

        typeRecyclerView = findViewById(R.id.typeRecyclerView);
        typeRecyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        typeRecyclerView.setLayoutManager(layoutManager);
        typeAdapter = new PokemonTypeAdapter(this);
        typeRecyclerView.setAdapter(typeAdapter);


        habilitiesRecyclerView = findViewById(R.id.habilitiesRecyclerView);
        habilitiesRecyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager2 = new GridLayoutManager(this, 3);
        habilitiesRecyclerView.setLayoutManager(layoutManager2);
        abilitiesAdapter = new PokemonAbilitiesAdapter(this);
        habilitiesRecyclerView.setAdapter(abilitiesAdapter);

    }

    private void getAbilities() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService2 pokemonService2 = retrofit.create(PokemonService2.class);
        Call<ListAbility> call = pokemonService2.getAbilities(id+"");
        call.enqueue(new Callback<ListAbility>() {
            @Override
            public void onResponse(Call<ListAbility> call, Response<ListAbility> response) {
                if(response != null) {
                    int tamanio = response.body().getAbilities().size();
                    ArrayList<String> types = new ArrayList<>();
                    for(int i=0; i<tamanio; i++) {
                        types.add(response.body().getAbilities().get(i).getAbility().getName());
                    }
                    abilitiesAdapter.addTypes(types);
                }
            }

            @Override
            public void onFailure(Call<ListAbility> call, Throwable t) {

            }
        });
    }

    private void getTypes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService2 pokemonService2 = retrofit.create(PokemonService2.class);
        Call<ListTypes> call = pokemonService2.getTypes(id+"");
        call.enqueue(new Callback<ListTypes>() {
            @Override
            public void onResponse(Call<ListTypes> call, Response<ListTypes> response) {
                if(response != null) {
                    int tamanio = response.body().getTypes().size();
                    ArrayList<String> types = new ArrayList<>();
                    for(int i=0; i<tamanio; i++) {
                        types.add(response.body().getTypes().get(i).getType().getName());
                    }
                    typeAdapter.addTypes(types);
                }
            }

            @Override
            public void onFailure(Call<ListTypes> call, Throwable t) {

            }
        });
    }

    private void getDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService2 pokemonService2 = retrofit.create(PokemonService2.class);
        Call<Pokemon> call = pokemonService2.getDetails(id+"");
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(response.isSuccessful()) {
                    switch (id.length()) {
                        case 1:
                            id = "00"+id;
                            break;
                        case 2:
                            id = "0"+id;
                    }
                    Glide.with(getApplicationContext())
                            .load("https://serebii.net/pokemongo/pokemon/" + id +".png")
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imagenImageView);
                    nameTextView.setText(response.body().getName().toUpperCase());
                    heightTextView.setText("Alto: " + response.body().getHeight()+"");
                    weightTextView.setText("Largo: " + response.body().getWeight()+"");
                    baseExperienceTextView.setText("Expericia base: " + response.body().getBase_experience()+"");
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }
}
