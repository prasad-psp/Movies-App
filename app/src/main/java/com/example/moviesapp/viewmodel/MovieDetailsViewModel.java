package com.example.moviesapp.viewmodel;

import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_ID_KEY;
import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_POSTER_KEY;
import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_TITLE_KEY;
import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_TYPE_KEY;
import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_YEAR_KEY;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.model.MovieEntity;
import com.example.moviesapp.repo.MovieDetailsRepo;

public class MovieDetailsViewModel extends ViewModel {

    private final MovieDetailsRepo repo;

    private final MutableLiveData<Boolean> movieSaveStatusLiveData;


    public MovieDetailsViewModel(Context context) {
        repo = new MovieDetailsRepo(context);
        movieSaveStatusLiveData = new MutableLiveData<>();
    }

    public LiveData<Boolean> observeMovieSaveStatusLiveData() {
        return movieSaveStatusLiveData;
    }

    public void saveMovie(String title,String movieId,String type,String poster, String year) {
        MovieEntity movie = new MovieEntity(
                0,
                movieId,
                title,
                type,
                poster,
                year
        );

        repo.saveMovie(movie, new MovieDetailsRepo.SaveMovieCallBack() {
            @Override
            public void onSuccess(MovieEntity movie) {
                movieSaveStatusLiveData.setValue(true);
            }

            @Override
            public void onFailed() {
                movieSaveStatusLiveData.setValue(false);
            }
        });
    }

    public void checkMovieAlreadyExistsOrNot(String movieId) {
        repo.getMovie(movieId, new MovieDetailsRepo.GetMovieCallBack() {
            @Override
            public void movieNotExists() {
                movieSaveStatusLiveData.setValue(false);
            }

            @Override
            public void movieAlreadyExists() {
                movieSaveStatusLiveData.setValue(true);
            }
        });
    }

    public String getMovieId(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(MOVIE_ID_KEY);
        }
        return "";
    }

    public String getTitle(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(MOVIE_TITLE_KEY);
        }
        return "";
    }

    public String getPoster(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(MOVIE_POSTER_KEY);
        }
        return "";
    }

    public String geType(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(MOVIE_TYPE_KEY);
        }
        return "";
    }

    public String getYear(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(MOVIE_YEAR_KEY);
        }
        return "";
    }
}
