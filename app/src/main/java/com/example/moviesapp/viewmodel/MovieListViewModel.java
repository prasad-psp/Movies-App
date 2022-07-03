package com.example.moviesapp.viewmodel;


import static com.example.moviesapp.viewmodel.SearchViewModel.MOVIE_KEY;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.moviesapp.R;
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

    public static final String MOVIE_TITLE_KEY = "movie_title";
    public static final String MOVIE_POSTER_KEY = "movie_poster";
    public static final String MOVIE_ID_KEY = "movie_id";
    public static final String MOVIE_TYPE_KEY = "movie_type";
    public static final String MOVIE_YEAR_KEY = "movie_year";

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

                    if(list != null && list.size() > 0) {
                        // data found
                        movieListLiveData.setValue(list);
                        errorLiveData.setValue("");
                    } else {
                        errorLiveData.setValue("Data not found please try again later");
                    }

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

    public void navigateToMovieDetailsScreen(View view, Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putString(MOVIE_TITLE_KEY, movie.getTitle());
        bundle.putString(MOVIE_POSTER_KEY, movie.getPoster());
        bundle.putString(MOVIE_ID_KEY, movie.getImdbID());
        bundle.putString(MOVIE_TYPE_KEY, movie.getType());
        bundle.putString(MOVIE_YEAR_KEY, movie.getYear());
        Navigation.findNavController(view).navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle);
    }

    private void setProgressVisible(boolean visible) {
        if(visible) {
            progressbarLiveData.setValue(View.VISIBLE);
        } else {
            progressbarLiveData.setValue(View.INVISIBLE);
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
