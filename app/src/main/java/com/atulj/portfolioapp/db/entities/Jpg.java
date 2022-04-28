package com.atulj.portfolioapp.db.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jpg {


    private String image_url;

    public Jpg(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
