package com.example.moviesapp.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.moviesapp.R;

public class SearchViewModel extends ViewModel {

    public static final String MOVIE_KEY = "movie_key";

    public void navigateToMovieListScreen(View view, String movieName) {
        // send movie name into
        Bundle bundle = new Bundle();
        bundle.putString(MOVIE_KEY,movieName);
        Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_movieListFragment,bundle);
    }
}
