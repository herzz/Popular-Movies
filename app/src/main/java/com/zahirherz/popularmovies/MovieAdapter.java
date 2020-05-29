package com.zahirherz.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zahirherz.popularmovies.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String TAG = "MovieAdapter";
    private final Context mContext;
    private final List<Movie> movieList;
    private static final String TITLE = "original_title";
    private static final String POSTER_PATH = "poster_path";
    private static final String OVERVIEW = "overview";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String RELEASE_DATE = "release_date";

    public MovieAdapter(Context context, List<Movie> movies) {
        this.mContext = context;
        this.movieList = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View movieView = layoutInflater.inflate(R.layout.movie_item_view, parent, false);
        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        Picasso.get()
                .load(movieList.get(position).getPosterPath())
                .error(R.drawable.ic_texture_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(mContext, MovieDetailActivity.class);
                        intent.putExtra(TITLE, movieList.get(position).getTitle());
                        intent.putExtra(POSTER_PATH, movieList.get(position).getPosterPath());
                        intent.putExtra(OVERVIEW, movieList.get(position).getOverview());
                        intent.putExtra(VOTE_AVERAGE, movieList.get(position).getVoteAverage());
                        intent.putExtra(RELEASE_DATE, movieList.get(position).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

}
