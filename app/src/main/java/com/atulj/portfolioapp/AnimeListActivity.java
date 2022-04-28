package com.atulj.portfolioapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
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
//      myList.setLayoutManager(new GridLayoutManager(this,2));
        myList.setHasFixedSize(false);
        EditText searchBar= findViewById(R.id.animeListSearchEditText);

        anime = new ArrayList<>();
//        anime.add(new Anime(R.drawable.naruto, "Naruto", "Moments prior to Naruto Uzumaki's birth, a huge demon known as the Kyuubi, the Nine-Tailed Fox, attacked Konohagakure, the Hidden Leaf Village, and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the Fourth Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. Now, Naruto is a hyperactive and knuckle-headed ninja still living in Konohagakure. Shunned because of the Kyuubi inside him, Naruto struggles to find his place in the village, while his burning desire to become the Hokage of Konohagakure leads him not only to some great new friends, but also some deadly foes.", "Naruto received numerous awards during its airing, including the \"Best Full-Length Animation Program Award\" in the Third UStv Awards and the 38th \"Best Animated Show\" in IGN's Top 100 Animated Series."));
//        anime.add(new Anime(R.drawable.deathnote, "DeathNote", "synopsis", "Death Note has been adapted into live action films, TV dramas, video games, light novels and a musical composed by a Broadway composer. It is licensed in North America by Viz Media."));
//        anime.add(new Anime(R.drawable.myhero, "My Hero Academia", "Moments prior to Naruto Uzumaki's birth, a huge demon known as the Kyuubi, the Nine-Tailed Fox, attacked Konohagakure, the Hidden Leaf Village, and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the Fourth Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto. Now, Naruto is a hyperactive and knuckle-headed ninja still living in Konohagakure. Shunned because of the Kyuubi inside him, Naruto struggles to find his place in the village, while his burning desire to become the Hokage of Konohagakure leads him not only to some great new friends, but also some deadly foes.", "Naruto received numerous awards during its airing, including the \"Best Full-Length Animation Program Award\" in the Third UStv Awards and the 38th \"Best Animated Show\" in IGN's Top 100 Animated Series."));

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
                    Log.e("Anime", "anime" + anime.getData().get(0).getBackground());
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
                Log.e("Anime", "anime" + anime.getData().get(0).getBackground());
                adapter.changeData(anime.getData());

            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }
}