package com.simanjit.moviesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkViweHolder> {
    private Context context;
    private ArrayList<BookmarkList> listBookMark;
//    private ArrayList<BookmarkList> mArrayListBookMark;
//    private BookmarkDatabaseAdapter bookmarkDatabaseAdapter;
    BookmarkAdapter(Context context, ArrayList<BookmarkList> listBookMark) {
        this.context = context;
        this.listBookMark = listBookMark;
//        this.mArrayListBookMark = listBookMark;
//        bookmarkDatabaseAdapter = new BookmarkDatabaseAdapter(context);
    }
    @Override
    public BookmarkViweHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmarklistsingleitem, parent, false);
        return new BookmarkViweHolder(view);
    }
    @Override
    public void onBindViewHolder(final BookmarkViweHolder holder, int position) {
        final BookmarkList bookmarkList = listBookMark.get(position);
        Glide.with(context)
                .load(bookmarkList.getThumbnail())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.noimage)
                .into(holder.thumbnail);
        holder.title.setText(bookmarkList.getTitle());

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.detailLayout.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(bookmarkList.getThumbnail())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .error(R.drawable.noimage)
                        .into(holder.thumbnailImage);

                holder.title1.setText(bookmarkList.getTitle());
                holder.released.setText("Released :- \n"+bookmarkList.getReleased());
                holder.runtime.setText("Runtime :- \n"+bookmarkList.getRuntime());
                holder.genre.setText("Genre :- \n"+bookmarkList.getGenre());
                holder.director.setText("Director :- \n"+bookmarkList.getDirector());
                holder.actors.setText("Actors :- \n"+bookmarkList.getActors());
                holder.language.setText("Language :- \n"+bookmarkList.getLanguage());
                holder.boxOffice.setText("BoxOffice :- \n"+bookmarkList.getBoxOffice());
                holder.production.setText("Production :- \n"+bookmarkList.getProduction());

                holder.closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      holder.detailLayout.setVisibility(View.GONE);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBookMark.size();
    }

}
