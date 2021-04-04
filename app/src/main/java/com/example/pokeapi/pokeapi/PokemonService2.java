package com.example.pokeapi.pokeapi;

import com.example.pokeapi.models.ListAbility;
import com.example.pokeapi.models.ListTypes;
import com.example.pokeapi.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService2 {
    @GET("pokemon/{id}")
    Call<Pokemon> getDetails(@Path("id") String id);

    @GET("pokemon/{id}")
    Call<ListAbility> getAbilities(@Path("id") String id);

    @GET("pokemon/{id}")
    Call<ListTypes> getTypes(@Path("id") String id);
}
