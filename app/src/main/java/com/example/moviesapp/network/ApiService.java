package com.example.moviesapp.network;

import com.example.moviesapp.network.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("?")
    Call<MovieResponse> getMovieBySearch(@Query("apikey") String apikey, @Query("s") String movieName);
}
