package com.simanjit.moviesearch;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.simanjit.moviesearch.util.BookmarkDatabaseAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BookmarkActivity extends AppCompatActivity {

    BookmarkDatabaseAdapter bookmarkDatabaseAdapter;
    ImageView thumbnail;
    TextView title;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        BookmarkActivity.this.setTitle("Bookmarked Movies");
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        bookmarkDatabaseAdapter=new BookmarkDatabaseAdapter(getApplicationContext());

        thumbnail = findViewById(R.id.thumbnail);
        title = findViewById(R.id.title);

        RecyclerView bookmarklistRV = findViewById(R.id.bookmarklistRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        bookmarklistRV.setLayoutManager(linearLayoutManager);
        bookmarklistRV.setHasFixedSize(true);
        bookmarkDatabaseAdapter = new BookmarkDatabaseAdapter(this);
        ArrayList<BookmarkList> allBookmark = bookmarkDatabaseAdapter.listBookmark();
        if (allBookmark.size() > 0) {
            bookmarklistRV.setVisibility(View.VISIBLE);
            BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(this, allBookmark);
            bookmarklistRV.setAdapter(bookmarkAdapter);
        }
        else {
            bookmarklistRV.setVisibility(View.GONE);
            Toast.makeText(this, "Bookmark is empty. Start adding now", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
