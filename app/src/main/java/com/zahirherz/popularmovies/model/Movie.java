package com.zahirherz.popularmovies.model;

import com.google.gson.annotations.SerializedName;
import com.zahirherz.popularmovies.util.Constants;

public class Movie {
    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("original_title")
    private final String title;

    private final String overview;

    @SerializedName("release_date")
    private final String releaseDate;

    public Movie(int voteAverage, String posterPath, String title, String overview, String releaseDate) {
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return Constants.BASE_IMAGE_URL + Constants.POSTER_IMAGE_SIZE + posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() { return releaseDate; }

}
