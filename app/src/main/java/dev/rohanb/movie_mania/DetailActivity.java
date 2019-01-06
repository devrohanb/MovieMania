package dev.rohanb.movie_mania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import static dev.rohanb.movie_mania.MainActivity.EXTRA_OVERVIEW;
import static dev.rohanb.movie_mania.MainActivity.EXTRA_RATINGS;
import static dev.rohanb.movie_mania.MainActivity.EXTRA_RELEASE_DATE;
import static dev.rohanb.movie_mania.MainActivity.EXTRA_TITLE;
import static dev.rohanb.movie_mania.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbarDetail = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbarDetail);

        Intent intent = getIntent();
        String posterUrl = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String overview = intent.getStringExtra(EXTRA_OVERVIEW);
        String releaseDate = intent.getStringExtra(EXTRA_RELEASE_DATE);
        Double ratings = intent.getDoubleExtra(EXTRA_RATINGS, 0.0);

        ImageView imgPosterDetail = findViewById(R.id.imgPosterDetail);
        TextView txtTitleDetail = findViewById(R.id.txtTitleDetail);
        TextView txtOverView = findViewById(R.id.txtOverView);
        TextView txtRatings = findViewById(R.id.txtRatings);
        TextView txtReleaseDateDetail = findViewById(R.id.txtReleaseDateDetail);
        Toolbar toolBarDetail = findViewById(R.id.toolbarDetail);

        Picasso.with(this).load("https://image.tmdb.org/t/p/w500/" + posterUrl).fit().centerInside().into(imgPosterDetail);

        txtTitleDetail.setText(title);
        txtOverView.setText(overview);
        txtRatings.setText(ratings.toString() + "/10 ");
        txtReleaseDateDetail.setText(releaseDate);
        toolBarDetail.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exmple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
}
