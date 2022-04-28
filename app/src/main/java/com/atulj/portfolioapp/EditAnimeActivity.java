package com.atulj.portfolioapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.atulj.portfolioapp.db.entities.Anime;
import com.atulj.portfolioapp.recyclerview.AnimeViewHolder;
import com.google.android.material.textfield.TextInputLayout;

public class EditAnimeActivity extends AppCompatActivity {
    private EditText name;
    private TextInputLayout background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_anime);
        Intent getIntent = getIntent();
        if (!getIntent.hasExtra(AnimeViewHolder.EXTRA_ANIME_TITLE)) {
            //Error extra wasn't passed
            //For now, let's Log it and Change back to Login Activity.  NOT a good way of handling this issue
            //Bad: Because user has no idea why.
            Log.e("EditActivity_onCreate", "Login Id not provided in Intent with Extra: " + AnimeViewHolder.EXTRA_ANIME_TITLE);
            finish();

        }
        name=findViewById(R.id.edit_name);

        String animeTitle=getIntent.getStringExtra(AnimeViewHolder.EXTRA_ANIME_TITLE);


    }
}