package com.example.moviesapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesapp.R;
import com.example.moviesapp.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;

    private static final String MOVIE_KEY = "movie_key";

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

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieName = binding.edtSearch.getText().toString().trim();

                if(!movieName.isEmpty()) {
                    // send movie name into
                    Bundle bundle = new Bundle();
                    bundle.putString(MOVIE_KEY,movieName);
                    Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_movieListFragment,bundle);
                    binding.edtSearch.setText("");
                }
            }
        });
    }
}