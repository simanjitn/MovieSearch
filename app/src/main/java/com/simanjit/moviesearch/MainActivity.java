package com.simanjit.moviesearch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.simanjit.moviesearch.util.APIService;
import com.simanjit.moviesearch.util.BookmarkDatabaseAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SearchView searchView ;
    APIService apiService;
    String querystring;
    ImageView thumbnail,thumbnailImage,bookmarkIcon;
    TextView title,title1,released,runtime,genre,director,actors,language,boxOffice,production;
    ConstraintLayout poster;
    RelativeLayout detailLayout;
    BookmarkDatabaseAdapter bookmarkDatabaseAdapter;
    public static String API_KEY="f34a648f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Search Movies");

        bookmarkDatabaseAdapter=new BookmarkDatabaseAdapter(getApplicationContext());

        thumbnail = findViewById(R.id.thumbnail);
        thumbnailImage = findViewById(R.id.thumbnailImage);
        title1 = findViewById(R.id.title1);
        released = findViewById(R.id.released);
        runtime = findViewById(R.id.runtime);
        genre = findViewById(R.id.genre);
        director = findViewById(R.id.director);
        actors = findViewById(R.id.actors);
        language = findViewById(R.id.language);
        boxOffice = findViewById(R.id.boxOffice);
        production = findViewById(R.id.production);
        title = findViewById(R.id.title);
        poster = findViewById(R.id.poster);
        bookmarkIcon = findViewById(R.id.bookmarkIcon);
        detailLayout = findViewById(R.id.detailLayout);


        searchView = findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }

        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length()<3){
                    Toast.makeText(getApplicationContext(),"Atleast 3 letter required to search",Toast.LENGTH_SHORT).show();
                }else {
                    querystring = query;
                    startSearch();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener()
        {

            @Override
            public boolean onClose()
            {
                poster.setVisibility(View.GONE);
                // TODO Auto-generated method stub
                return false;
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_bookmark:
                startActivity(new Intent(getApplicationContext(),BookmarkActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void startSearch() {


        if (this.apiService == null) {
            this.apiService = new APIService();
        }

        SearchMovieApi searchMovieApi = APIService.getClient().create(SearchMovieApi.class);

        searchMovieApi.getMovieDetail(querystring, API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, final Response<MovieResponse> response) {

                if (response.isSuccessful()) {

                    poster.setVisibility(View.VISIBLE);

                    Glide.with(getApplicationContext())
                            .load(response.body().getPoster())
//                            .placeholder(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .error(R.drawable.noimage)
                            .into(thumbnail);
                    title.setText(response.body().getTitle());

                    Glide.with(getApplicationContext())
                            .load(response.body().getPoster())
//                            .placeholder(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .error(R.drawable.noimage)
                            .into(thumbnailImage);

                    title1.setText(response.body().getTitle());
                    released.setText("Released :- \n"+response.body().getReleased());
                    runtime.setText("Runtime :- \n"+response.body().getRuntime());
                    genre.setText("Genre :- \n"+response.body().getGenre());
                    director.setText("Director :- \n"+response.body().getDirector());
                    actors.setText("Actors :- \n"+response.body().getActors());
                    language.setText("Language :- \n"+response.body().getLanguage());
                    boxOffice.setText("BoxOffice :- \n"+response.body().getBoxOffice());
                    production.setText("Production :- \n"+response.body().getProduction());

                    bookmarkIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bookmarkDatabaseAdapter.insertEntry(response.body().getPoster(),response.body().getTitle(),response.body().getReleased(),response.body().getRuntime(),response.body().getGenre(),response.body().getDirector(),response.body().getActors(),response.body().getLanguage(),response.body().getBoxOffice(),response.body().getProduction());
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "No Movies Available", Toast.LENGTH_LONG).show();
                    Log.d("STATUS", "ResponseFailed " + response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("STATUS", "ResponseRequestFailed " + t);
                Toast.makeText(getApplicationContext(), "Sorry! Unexpeted Error", Toast.LENGTH_LONG).show();
//                    loginView.checkLoginStatus("", "Login Failed","","");
            }
        });
    }

    public void poster(View view) {
        detailLayout.setVisibility(View.VISIBLE);

    }

    public void closeBtn(View view) {
        detailLayout.setVisibility(View.GONE);
    }


}
