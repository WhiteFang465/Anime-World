package com.atulj.portfolioapp.db.entities;

import java.util.List;

public class Search {

    List<Anime> data;
    Anime singleData;

    public Anime getSingleData() {
        return singleData;
    }

    public void setSingleData(Anime singleData) {
        this.singleData = singleData;
    }

    public List<Anime> getData() {
        return data;
    }

    public void setData(List<Anime> data) {
        this.data = data;
    }

    public Search(List<Anime> data) {
        this.data = data;
    }
}
