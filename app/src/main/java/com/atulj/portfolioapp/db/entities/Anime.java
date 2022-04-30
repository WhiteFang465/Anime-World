package com.atulj.portfolioapp.db.entities;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity
public class Anime {

    private int id;
    private String title;
    private String synopsis;
    private String background;
    private Images images;
    @SerializedName("mal_id")
    private int animeId;
    private double score;
    private int rank;
    private int episodes;
    private String status;


    public Anime( String title, String synopsis, String background, Images images, int animeId, double score, int rank, int episodes, String status) {
        this.title = title;
        this.synopsis = synopsis;
        this.background = background;
        this.images = images;
        this.animeId = animeId;
        this.score = score;
        this.rank = rank;
        this.episodes = episodes;
        this.status = status;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
