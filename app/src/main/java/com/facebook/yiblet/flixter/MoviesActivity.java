package com.facebook.yiblet.flixter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
//
//        1. get the actual mvoies
        ArrayList<Movie> movies = Movie.getFakeMovies();

//        2. get the ListView that we want to populate
        ListView lvMovies = (ListView)findViewById(R.id.lvMovies);
//        3. create an ArrayAdapter
//        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
        MoviesAdapter  adapter = new MoviesAdapter(this, movies);
//        4. Associate the adapter with uddkdevgkcukreguhtlihutihbjndldhthe ListView
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
            lvMovies.setDivider(null);
        }
    }
}
