package com.example.moviesapp.repo;

import android.content.Context;

import com.example.moviesapp.model.MovieEntity;
import com.example.moviesapp.room.MovieDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDetailsRepo {

    private final ExecutorService executorService;

    private final Context context;

    public MovieDetailsRepo(Context context) {
        this.context = context;
        executorService = Executors.newSingleThreadExecutor();
    }


    public void getMovie(String movieId, GetMovieCallBack getMovieCallBack) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    MovieEntity movie = MovieDatabase.getInstance(context).dao().getMovie(movieId);

                    if(movie != null) {
                        getMovieCallBack.movieAlreadyExists();
                    } else {
                        getMovieCallBack.movieNotExists();
                    }
                }
            });
    }

    public void saveMovie(MovieEntity movie, SaveMovieCallBack movieCallBack) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                long result = MovieDatabase.getInstance(context).dao().addMovie(movie);

                if(result > 0) {
                    movieCallBack.onSuccess(movie);
                } else {
                    movieCallBack.onFailed();
                }
            }
        });
    }


    public interface GetMovieCallBack {
        void movieNotExists();
        void movieAlreadyExists();
    }

    public interface SaveMovieCallBack {
        void onSuccess(MovieEntity movie);
        void onFailed();
    }
}
