package com.example.moviesapp.viewmodel;

import static com.example.moviesapp.view.SearchFragment.MOVIE_KEY;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.model.Movie;
import com.example.moviesapp.network.response.MovieResponse;
import com.example.moviesapp.repo.MovieListRepo;

import java.util.ArrayList;

import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private final MovieListRepo repo;

    private final MutableLiveData<ArrayList<Movie>> movieListLiveData;

    private final MutableLiveData<Integer> progressbarLiveData;

    private final MutableLiveData<String> errorLiveData;

    public MovieListViewModel() {
        repo = new MovieListRepo();
        movieListLiveData = new MutableLiveData<>();
        progressbarLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Movie>> observeMovieListData() {
        return movieListLiveData;
    }

    public LiveData<Integer> observeProgressLiveData() {
        return progressbarLiveData;
    }

    public LiveData<String> observeErrorLiveData() {
        return errorLiveData;
    }

    public void getMovieList(Bundle bundle) {
        setProgressVisible(true);

        String movieName = getMovieNameFromIntent(bundle);

        if(movieName.isEmpty()) {
            errorLiveData.setValue("Movie name is empty");
            setProgressVisible(false);
            return;
        }

        // request movie list data
        repo.requestMovieList("enter_your_omdbapi_apikey", movieName, new MovieListRepo.MovieRepoCallback() {
            @Override
            public void onSuccess(Response<MovieResponse> response) {
                if(response.body() != null) {
                    MovieResponse movieResponse = response.body();
                    ArrayList<Movie> list = (ArrayList<Movie>) movieResponse.getMovieList();

                    if(list.size() > 0) {
                        // data found
                        movieListLiveData.setValue(list);
                    }

                    errorLiveData.setValue("");
                    setProgressVisible(false);
                }
            }

            @Override
            public void onFailed(Throwable t) {
                // error found
                errorLiveData.setValue(t.getMessage());
                setProgressVisible(false);
            }
        });
    }

    private void setProgressVisible(boolean visible) {
        if(visible) {
            progressbarLiveData.setValue(View.VISIBLE);
        } else {
            progressbarLiveData.setValue(View.GONE);
        }
    }

    private String getMovieNameFromIntent(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(MOVIE_KEY);
        } else {
            return "";
        }
    }
}
