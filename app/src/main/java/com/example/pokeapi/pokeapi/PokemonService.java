package com.example.pokeapi.pokeapi;



import com.example.pokeapi.models.PokemonRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("pokemon")
    Call<PokemonRespuesta>obtenerListaPokemon(@Query("limit")int limit, @Query("offset")int offset);
}
