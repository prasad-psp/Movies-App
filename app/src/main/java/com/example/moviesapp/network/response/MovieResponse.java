package com.example.moviesapp.network.response;

import com.example.moviesapp.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("Search")
    private List<Movie> movieList = null;

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("Response")
    private String response;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> search) {
        this.movieList = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
