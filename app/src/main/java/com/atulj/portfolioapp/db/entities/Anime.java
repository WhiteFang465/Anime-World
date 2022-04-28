package com.atulj.portfolioapp.db.entities;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Anime {


    private String title;
    private String synopsis;
    private String background;
    private Images images;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Anime(String title, String synopsis, String background, Images images) {
        this.title = title;
        this.synopsis = synopsis;
        this.background = background;
        this.images = images;
    }
}
