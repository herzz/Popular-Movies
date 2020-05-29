package com.zahirherz.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesList {

    @SerializedName("results")
    private final List<Movie> movieList = null;

    public List<Movie> getMovieList() {
        return movieList;
    }

}
