package com.example.moviesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesapp.R;
import com.example.moviesapp.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;

    private final ArrayList<Movie> list = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public  MovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Movie> movies) {
        clearData();
        list.addAll(movies);
        notifyDataSetChanged();
    }

    public void clearData() {
        if(list.size() > 0) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = list.get(position);

        if(movie != null) {
            Glide.with(context)
                    .load(movie.getPoster())
                    .apply(RequestOptions.centerInsideTransform())
                    .into(holder.imgPoster);
        }

        holder.rootLayout.setOnClickListener(view -> {
            if(movie != null) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClicked(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(Movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPoster;
        private ConstraintLayout rootLayout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgMovieItem);
            rootLayout = itemView.findViewById(R.id.movieListItemRootLayout);
        }
    }
}
