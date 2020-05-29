package com.zahirherz.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.zahirherz.popularmovies.databinding.ActivityMovieDetailBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailActivity";
    private static final String TITLE = "original_title";
    private static final String POSTER_PATH = "poster_path";
    private static final String OVERVIEW = "overview";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String RELEASE_DATE = "release_date";

    private TextView title;
    private TextView overview;
    private ImageView posterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.zahirherz.popularmovies.databinding.ActivityMovieDetailBinding binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        if (intent.hasExtra(TITLE)) {
            String title = Objects.requireNonNull(intent.getExtras()).getString(TITLE);
            String movieOverview = intent.getExtras().getString(OVERVIEW);
            String imageURL = intent.getExtras().getString(POSTER_PATH);
            String average = "* " + intent.getExtras().getDouble(VOTE_AVERAGE);
            String releaseDate = "Release Date: \n" + formatReleaseDate(intent);

            binding.textViewTitle.setText(title);
            binding.textViewOverview.setText(movieOverview);
            binding.textViewAverageRating.setText(average);
            binding.textViewReleaseDate.setText(releaseDate);

            Picasso.get()
                    .load(imageURL)
                    .into(binding.moviePoster);
        }
    }

    private String formatReleaseDate(Intent intent) {
        String inputReleaseDate = Objects.requireNonNull(intent.getExtras()).getString(RELEASE_DATE);
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-dd-MM");
        assert inputReleaseDate != null;
        Date date = null;
        try {
            date = inputFormat.parse(inputReleaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return outputFormat.format(date);
    }
}
