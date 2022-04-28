package com.atulj.portfolioapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atulj.portfolioapp.R;
import com.atulj.portfolioapp.db.entities.Anime;

import java.util.ArrayList;
import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeViewHolder> {
    private List<Anime> data = new ArrayList<>();
    private Context context;
    public void add(Anime anime) {
        data.add(anime);
        notifyDataSetChanged();// not great as it requires lot of resources.
    }

    public void remove(Anime anime) {
        data.remove(anime);
        notifyDataSetChanged();
    }

    public void changeData(List<Anime> anime) {
        data = anime;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflaterView = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);// False because the recycler will add to the view hierarchy
         context = parent.getContext();
        return new AnimeViewHolder(inflaterView,this);

    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.anime_view_items;
    }
}
