package com.facebook.yiblet.flixter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yiblet on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {
    ArrayList<Movie> list;

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
        this.list = movies;
        retrieveMovies();
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivPoster;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lookup view for data population

        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(movie.title);
        viewHolder.tvDescription.setText(movie.description);

        String imageUri = movie.posterUrl;
        Picasso.with(getContext()).load(imageUri)
                .placeholder(convertView.getResources().getDrawable(android.R.drawable.ic_menu_report_image))
//                .resize(780, 0)
                .into(viewHolder.ivPoster);
        // Return the completed view to render on screen
        return convertView;

    }

    public void retrieveMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        final MoviesAdapter that = this;
        params.put("api_key", "a07e22bc18f5cb106bfe4cc1f83ad8ed");
        client.get("https://api.themoviedb.org/3/movie/now_playing", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray movies = response.getJSONArray("results");
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject movie = movies.getJSONObject(i);
                        String path;
                        int orientation = getContext().getResources().getConfiguration().orientation;
                        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            path = "backdrop_path";
                        } else {
                            path = "poster_path";
                        }
                        list.add(new Movie(movie.getString("title"), "https://image.tmdb.org/t/p/w780" + movie.getString(path),
                                (movie.getDouble("vote_average")), movie.getString("overview"), movie));
                    }
                    that.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
