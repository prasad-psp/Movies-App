package com.example.moviesapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviesapp.model.MovieEntity;

@Database(entities = {MovieEntity.class},version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    private static final String DB_NAME = "movie_db";

    public static synchronized MovieDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,MovieDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract MovieDao dao();
}
