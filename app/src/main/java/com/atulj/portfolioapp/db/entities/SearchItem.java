package com.atulj.portfolioapp.db.entities;

import java.util.List;

public class SearchItem {

    public List<Search> anime;

    public SearchItem(List<Search> anime) {
        this.anime = anime;
    }

    public void setAnime(List<Search> anime) {
        this.anime = anime;
    }

    public List<Search> getAnime(){
        return anime;
    }
}
