package com.atulj.portfolioapp.db.entities;

import java.util.List;

public class Search {

    List<Anime> data;

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
