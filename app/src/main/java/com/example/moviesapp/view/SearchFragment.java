package com.example.moviesapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesapp.databinding.FragmentSearchBinding;
import com.example.moviesapp.viewmodel.SearchViewModel;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;

    private SearchViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieName = binding.edtSearch.getText().toString().trim();

                if(!movieName.isEmpty()) {
                    // send movie name into
                    viewModel.navigateToMovieListScreen(view, movieName);
                    binding.edtSearch.setText("");
                }
            }
        });
    }

    private void init() {
        // init view model
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }
}