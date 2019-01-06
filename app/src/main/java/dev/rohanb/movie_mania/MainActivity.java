package dev.rohanb.movie_mania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imagePosterUrl";
    public static final String EXTRA_TITLE = "movieTitle";
    public static final String EXTRA_OVERVIEW = "overView";
    public static final String EXTRA_RATINGS = "ratings";
    public static final String EXTRA_RELEASE_DATE = "releaseDate";
    public static final String EXTRA_ADULT= "adult";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarMain = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbarMain);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exmple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item0:
                Intent intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                return true;

            case R.id.item1:
                intent = new Intent(this, AboutUs.class);
                startActivity(intent);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void parseJSON()
    {
            String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=e08dc74dbbfe0b96ad8d8f7a4699b50b";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for(int i=0; i<jsonArray.length();i++)
                            {
                                JSONObject result = jsonArray.getJSONObject(i);

                                String title = result.getString("title");
                                String overView = result.getString("overview");
                                String releaseDate = result.getString("release_date");
                                String posterUrl = result.getString("poster_path");
                                Boolean adult = result.getBoolean("adult");
                                Double ratings = result.getDouble("vote_average");

                                mExampleList.add(new ExampleItem(posterUrl,title,releaseDate,adult,overView,ratings));

                            }
                            mExampleAdapter = new ExampleAdapter(MainActivity.this,mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detail_Intent = new Intent(this,DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);

        detail_Intent.putExtra(EXTRA_URL, clickedItem.getmPosterUrl());
        detail_Intent.putExtra(EXTRA_TITLE, clickedItem.getmTitle());
        detail_Intent.putExtra(EXTRA_OVERVIEW, clickedItem.getmOverView());
        detail_Intent.putExtra(EXTRA_RATINGS, clickedItem.getmRatings());
        detail_Intent.putExtra(EXTRA_RELEASE_DATE, clickedItem.getmReleaseDate());
        detail_Intent.putExtra(EXTRA_ADULT, clickedItem.getmAdult());

        startActivity(detail_Intent);
    }

}
