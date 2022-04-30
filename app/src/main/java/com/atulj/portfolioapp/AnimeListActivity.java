package com.atulj.portfolioapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atulj.portfolioapp.db.entities.Anime;
import com.atulj.portfolioapp.db.entities.Search;
import com.atulj.portfolioapp.db.network.AnimeListDb;
import com.atulj.portfolioapp.recyclerview.AnimeAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeListActivity extends AppCompatActivity {
    private ArrayList<Anime> anime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);
        RecyclerView myList = findViewById(R.id.main_recyclerView);
        myList.setLayoutManager(new LinearLayoutManager(this));
       // myList.setLayoutManager(new GridLayoutManager(this,2));
        myList.setHasFixedSize(false);
        EditText searchBar = findViewById(R.id.animeListSearchEditText);

        anime = new ArrayList<>();

        AnimeAdapter adapter = new AnimeAdapter();
        adapter.changeData(anime);
        myList.setAdapter(adapter);
        Call<Search> animeSearch = AnimeListDb.service.searchAnime(searchBar.getText().toString(), 1);

        findViewById(R.id.animeListSearchButton).setOnClickListener(v -> {
            Call<Search> animeSearchAfterClick = AnimeListDb.service.searchAnime(searchBar.getText().toString(), 1);
            animeSearchAfterClick.enqueue(new Callback<Search>() {
                @Override
                public void onResponse(Call<Search> call, Response<Search> response) {
                    Search anime = response.body();
                    adapter.changeData(anime.getData());

                }

                @Override
                public void onFailure(Call<Search> call, Throwable t) {

                }
            });
        });

        animeSearch.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                Search anime = response.body();
                adapter.changeData(anime.getData());

            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }
}