package com.simanjit.moviesearch;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class BookmarkViweHolder extends RecyclerView.ViewHolder {
    ConstraintLayout poster;
    TextView title,title1,released,runtime,genre,director,actors,language,boxOffice,production;
    ImageView thumbnail,thumbnailImage;
    RelativeLayout detailLayout;
    Button closeBtn;
BookmarkViweHolder(View itemView) {
        super(itemView);
    poster = itemView.findViewById(R.id.poster);
    detailLayout = itemView.findViewById(R.id.detailLayout);
    title = itemView.findViewById(R.id.title);
    thumbnail = itemView.findViewById(R.id.thumbnail);
    thumbnailImage = itemView.findViewById(R.id.thumbnailImage);
    closeBtn = itemView.findViewById(R.id.closeBtn);
    thumbnailImage = itemView.findViewById(R.id.thumbnailImage);
    title1 = itemView.findViewById(R.id.title1);
    released = itemView.findViewById(R.id.released);
    runtime = itemView.findViewById(R.id.runtime);
    genre = itemView.findViewById(R.id.genre);
    director = itemView.findViewById(R.id.director);
    actors = itemView.findViewById(R.id.actors);
    language = itemView.findViewById(R.id.language);
    boxOffice = itemView.findViewById(R.id.boxOffice);
    production = itemView.findViewById(R.id.production);

    }
}
