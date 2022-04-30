package com.atulj.portfolioapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.atulj.portfolioapp.db.entities.Data;
import com.atulj.portfolioapp.db.network.AnimeListDb;
import com.atulj.portfolioapp.recyclerview.AnimeViewHolder;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeSearchActivity extends AppCompatActivity {
    private int animeId;

    private ImageView avatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_search);
        Intent getData = new Intent();
        getData = getIntent();
        avatar = findViewById(R.id.search_ImageView);

        if (getData.hasExtra(AnimeViewHolder.EXTRA_ANIME_TITLE)) {
            animeId = getData.getIntExtra(AnimeViewHolder.EXTRA_ANIME_TITLE, -1);

        }

        Call<Data> animeCall = AnimeListDb.service.searchAnimeById(animeId);
        animeCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data anime = response.body();
                TextView name = findViewById(R.id.search_Title);
                TextView rank = findViewById(R.id.search_rank);
                TextView episodes = findViewById(R.id.search_Episodes);
                TextView status= findViewById(R.id.search_Status);
                TextView Synopsis= findViewById(R.id.search_Synopsis);

                name.setText(anime.getData().getTitle());
                rank.setText(String.valueOf(anime.getData().getRank()));
                Picasso.get().load(anime.getData().getImages().getJpg().getImage_url()).into(avatar);
                Synopsis.setText(anime.getData().getSynopsis());
                status.setText(anime.getData().getStatus());
                episodes.setText(String.valueOf(anime.getData().getEpisodes()));

                Log.e("Anime Id", "Anime Id" + anime.getData().getTitle());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("Log anime", t.toString());
            }
        });


    }
}