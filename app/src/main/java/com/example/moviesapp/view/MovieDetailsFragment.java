package com.example.moviesapp.view;

import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_POSTER_KEY;
import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_TITLE_KEY;
import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_TYPE_KEY;
import static com.example.moviesapp.viewmodel.MovieListViewModel.MOVIE_YEAR_KEY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesapp.R;
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getTitle();
        if(!title.isEmpty()) {
            binding.txtMovieName.setText(title);
        }

        String poster = getPoster();
        if(!poster.isEmpty()) {
            Glide.with(this.requireContext())
                    .load(poster)
                    .apply(RequestOptions.centerInsideTransform())
                    .into(binding.imgMoviePoster);
        }

        String type = geType();
        if(!type.isEmpty()) {
            binding.txtMovieType.setText(type);
        }

        String year = getYear();
        if(!year.isEmpty()) {
            binding.txtMovieYear.setText(year);
        }
    }

    public String getTitle() {
        if(getArguments() != null) {
            return getArguments().getString(MOVIE_TITLE_KEY);
        }
        return "";
    }

    public String getPoster() {
        if(getArguments() != null) {
            return getArguments().getString(MOVIE_POSTER_KEY);
        }
        return "";
    }

    public String geType() {
        if(getArguments() != null) {
            return getArguments().getString(MOVIE_TYPE_KEY);
        }
        return "";
    }

    public String getYear() {
        if(getArguments() != null) {
            return getArguments().getString(MOVIE_YEAR_KEY);
        }
        return "";
    }
}