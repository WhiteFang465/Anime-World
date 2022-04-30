package com.atulj.portfolioapp.db.network;

import com.atulj.portfolioapp.db.entities.Anime;
import com.atulj.portfolioapp.db.entities.Data;
import com.atulj.portfolioapp.db.entities.Search;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimeListDb {

    String BASE_URL = "https://api.jikan.moe";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //  https://api.jikan.moe/v4/anime?q=naruto&sfw=1
//    https://api.jikan.moe/v4/anime/{id}
    AnimeListDb service = retrofit.create(AnimeListDb.class);

    @GET("v4/anime")
    Call<Search> searchAnime(@Query(value = "q", encoded = true) String q,
                             @Query(value = "sfw")int sfw);
    @GET("/v4/anime/{id}")
    Call <Data> searchAnimeById(@Path("id")int id);
}
