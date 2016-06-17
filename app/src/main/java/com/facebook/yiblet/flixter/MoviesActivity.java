package com.facebook.yiblet.flixter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;

public class MoviesActivity extends AppCompatActivity {
    @BindView (R.id.lvMovies) ListView list;
    MoviesAdapter adapter;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
//        (View) findViewById(R.id.container).setBackground(R.drawable.rect);
//
//        1. get the actual mvoies
        ArrayList<Movie> movies = Movie.newMovies();
        this.movies = movies;

//        2. get the ListView that we want to populate
        ListView lvMovies = (ListView)findViewById(R.id.lvMovies);
        list = lvMovies;

//        3. create an ArrayAdapter
//        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
        MoviesAdapter  adapter = new MoviesAdapter(this, movies);
        this.adapter = adapter;
//        4. Associate the adapter with uddkdevgkcukreguhtlihutihbjndldhthe ListView
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
            lvMovies.setDivider(null);
        }
        setlvOnclickActions();
    }

    private void setlvOnclickActions(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(MoviesActivity.this, MovieDescriptionActivity.class);
                    Movie movie = movies.get(position);
                    i.putExtra("description", movie.description);
                    i.putExtra("title", movie.title);
                    i.putExtra("posterUrl", movie.posterUrl);
                    i.putExtra("rating", movie.rating);
                    i.putExtra("popularity", movie.popularity);
                    i.putExtra("poster_path", movie.poster_path);
                    i.putExtra("backdrop_path", movie.backdrop_path);
                    i.putExtra("id", movie.id);
                    startActivity(i);
                }
            }
        );
    }

    public View getViewByPosition(int pos) {
        final int firstListItemPosition = list.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + list.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return list.getAdapter().getView(pos, null, list);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return list.getChildAt(childIndex);
        }
    }
}
