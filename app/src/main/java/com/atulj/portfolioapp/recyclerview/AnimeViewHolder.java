package com.atulj.portfolioapp.recyclerview;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atulj.portfolioapp.EditAnimeActivity;
import com.atulj.portfolioapp.R;
import com.atulj.portfolioapp.db.entities.Anime;
import com.squareup.picasso.Picasso;

public class AnimeViewHolder extends RecyclerView.ViewHolder {
    public static final String EXTRA_ANIME_TITLE = "animeTitle";
    private final ImageView avatar;
    private final TextView message;
    private final TextView name;
    private AnimeAdapter adapter;
    private Anime animeEntity;

    public AnimeViewHolder(@NonNull View itemView, @NonNull AnimeAdapter adapter) {
        super(itemView);
        this.adapter = adapter;

        avatar = itemView.findViewById(R.id.animeListViewItem_avatar);
        message = itemView.findViewById(R.id.animeListViewItem_message);
        name = itemView.findViewById(R.id.animeListViewItem_name);

        itemView.setOnLongClickListener(v -> {
            Toast.makeText(itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            adapter.remove(animeEntity);
            return true;
        });
        itemView.setOnClickListener(q -> {
            Intent editAnime = new Intent(itemView.getContext(), EditAnimeActivity.class)
                    .putExtra(EXTRA_ANIME_TITLE, animeEntity.getTitle());

            itemView.getContext().startActivity(editAnime);

        });
//        itemView.setOnTouchListener(q->{
//            public boolean onTouch(View q, MotionEvent event) {
//                // ... Respond to touch events
//                return true;
//            }
//        });

    }

    public void bind(Anime entity) {
        this.animeEntity = entity;
        Picasso.get().load(entity.getImages().getJpg().getImage_url()).into(avatar);
        name.setText(entity.getTitle());
        message.setText(entity.getBackground());
    }
}
