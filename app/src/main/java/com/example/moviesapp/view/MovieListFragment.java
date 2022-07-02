package com.example.moviesapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesapp.R;
import com.example.moviesapp.adapter.MovieAdapter;
import com.example.moviesapp.databinding.FragmentMovieListBinding;
import com.example.moviesapp.databinding.MovieListItemBinding;

public class MovieListFragment extends Fragment {

    private FragmentMovieListBinding binding;

    private MovieAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieListBinding.inflate(inflater,container,false);
        adapter = new MovieAdapter();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {
        // init recycler view
        binding.recyclerMovie.setLayoutManager(new GridLayoutManager(this.requireContext(),2));
        binding.recyclerMovie.setHasFixedSize(true);
        // set movie list adapter
        binding.recyclerMovie.setAdapter(adapter);
    }
}