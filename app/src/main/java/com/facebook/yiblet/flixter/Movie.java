package com.facebook.yiblet.flixter;

import java.util.ArrayList;

public class Movie {
    public String title;
    public String posterUrl;
    public double rating;
    public String description;


    public Movie(String title, String posterUrl, double rating, String description) {
        this.rating = rating;
        this.title = title;
        this.posterUrl = posterUrl;
        this.description = description;
    }

    public static ArrayList<Movie> getFakeMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        return movies;
    }

    @Override
    public String toString() {
        return this.title + ": " + this.rating;
    }

}
