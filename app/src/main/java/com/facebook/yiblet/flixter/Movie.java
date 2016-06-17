package com.facebook.yiblet.flixter;

import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    public String title;
    public String posterUrl;
    public double rating;
    public String description;
    protected JSONObject raw;
    public boolean adult;
    public String overview;
    public String release_date;
    public double popularity;
    public double vote;
    public String backdrop_path;
    public String poster_path;
    public String id;



    public Movie(String title, String posterUrl, double rating, String description, JSONObject raw) {
        this.rating = rating;
        this.title = title;
        this.posterUrl = posterUrl;
        this.description = description;
        this.raw = raw;
        try {
            this.adult = raw.getBoolean("adult");
            this.overview = description;
            this.release_date = raw.getString("release_date");
            this.popularity = raw.getDouble("popularity");
            this.vote = raw.getDouble("vote_average");
            this.backdrop_path = "https://image.tmdb.org/t/p/w780" + raw.getString("backdrop_path");
            this.poster_path = "https://image.tmdb.org/t/p/w780" + raw.getString("poster_path");
            this.id = new Integer(raw.getInt("id")).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Movie> newMovies() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.title + ": " + this.rating;
    }

}
