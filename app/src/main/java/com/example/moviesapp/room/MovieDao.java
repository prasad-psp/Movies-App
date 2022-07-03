package com.example.moviesapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.moviesapp.model.MovieEntity;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovie(MovieEntity movie);

    @Query("SELECT * FROM movie ORDER BY id DESC")
    LiveData<MovieEntity> getMovieList();

    @Query("SELECT * FROM movie WHERE movie_id = :movieId")
    MovieEntity getMovie(String movieId);
}
