package com.facebook.yiblet.flixter;

import java.util.ArrayList;

public class Movie {
    public String title;
    public String posterUrl;
    public int rating;

    public Movie(String title, String posterUrl, int rating) {
        this.rating = rating;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public static ArrayList<Movie> getFakeMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            movies.add(new Movie("The Social Network", "", 75));
            movies.add(new Movie("The Internship", "", 50));
            movies.add(new Movie("The Lion King", "", 100));
        }
        return movies;
    }

    @Override
    public String toString() {
        return this.title + ": " + this.rating;
    }
}
