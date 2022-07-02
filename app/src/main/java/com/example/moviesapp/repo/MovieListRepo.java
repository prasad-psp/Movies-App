package com.example.moviesapp.repo;

import com.example.moviesapp.network.ApiService;
import com.example.moviesapp.network.RetrofitInstance;
import com.example.moviesapp.network.response.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListRepo {

    private final ApiService apiService;

    public MovieListRepo() {
        apiService = RetrofitInstance.getInstance().create(ApiService.class);
    }

    public void requestMovieList(String apikey, String movieName, MovieRepoCallback callback) {

        Call<MovieResponse> call = apiService.getMovieBySearch(apikey,movieName);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onFailed(t);
            }
        });

    }


    interface MovieRepoCallback {
        void onSuccess(Response<MovieResponse> response);
        void onFailed(Throwable t);
    }
}
