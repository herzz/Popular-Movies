package com.zahirherz.popularmovies.requests;

import com.zahirherz.popularmovies.model.MoviesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("3/movie/top_rated")
    Call<MoviesList> getTopRated(@Query("api_key") String apiKey);

    @GET("3/movie/popular")
    Call<MoviesList> getMostPopular(@Query("api_key") String apiKey);
}
