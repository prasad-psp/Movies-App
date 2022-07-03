package com.example.moviesapp.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AppViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public AppViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == MovieDetailsViewModel.class) {
            return (T) new MovieDetailsViewModel(context);
        }
        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
