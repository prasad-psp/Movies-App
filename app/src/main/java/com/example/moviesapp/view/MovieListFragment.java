package com.example.moviesapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moviesapp.adapter.MovieAdapter;
import com.example.moviesapp.databinding.FragmentMovieListBinding;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.viewmodel.MovieListViewModel;

import java.util.ArrayList;

public class MovieListFragment extends Fragment {

    private FragmentMovieListBinding binding;

    private MovieAdapter adapter;

    private MovieListViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

        adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, Movie movie) {
                // movie list item clicked
                viewModel.navigateToMovieDetailsScreen(view, movie);
            }
        });

        viewModel.observeMovieListData().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                if (movies != null && movies.size() > 0) {
                    adapter.setData(movies);
                }
            }
        });

        viewModel.observeProgressLiveData().observe(this.getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                binding.progressCircular.setVisibility(visibility);
            }
        });

        viewModel.observeErrorLiveData().observe(this.getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    Toast.makeText(MovieListFragment.this.requireContext(), "Something went wrong!!\n" + s, Toast.LENGTH_SHORT).show();
                }
            }
        });

        adapter.clearData();
        viewModel.getMovieList(getArguments());
    }

    private void init() {
        // init adapter
        adapter = new MovieAdapter(this.requireContext());
        // init view model
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        // init recycler view
        binding.recyclerMovie.setLayoutManager(new GridLayoutManager(this.requireContext(), 2));
        binding.recyclerMovie.setHasFixedSize(true);
        // set movie list adapter
        binding.recyclerMovie.setAdapter(adapter);
    }
}