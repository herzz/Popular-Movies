package com.zahirherz.popularmovies;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zahirherz.popularmovies.databinding.ActivityMainBinding;
import com.zahirherz.popularmovies.model.Movie;
import com.zahirherz.popularmovies.model.MoviesList;
import com.zahirherz.popularmovies.requests.MovieApi;
import com.zahirherz.popularmovies.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private List<Movie> movieList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }

    private void init() {
        recyclerView = binding.rvMovies;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        getMostPopular();
    }

    private void getMostPopular() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<MoviesList> call = movieApi.getMostPopular(Constants.API_KEY);

        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "There was an issue loading: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Movie> movies = response.body().getMovieList();

                MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), movies);
                recyclerView.setAdapter(movieAdapter);
                recyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "There was an issue: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTopRated() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<MoviesList> call = movieApi.getTopRated(Constants.API_KEY);

        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "There was an issue loading: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Movie> movies = response.body().getMovieList();

                MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), movies);
                recyclerView.setAdapter(movieAdapter);
                recyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "There was an issue: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.most_popular) {
            getMostPopular();
            return true;
        } else {
            getTopRated();
        }
        return super.onOptionsItemSelected(item);
    }
}
