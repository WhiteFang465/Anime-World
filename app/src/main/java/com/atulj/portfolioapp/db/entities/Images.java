package com.atulj.portfolioapp.db.entities;

import java.util.List;

public class Images {
    Jpg jpg;

    public Jpg getJpg() {
        return jpg;
    }

    public void setJpg(Jpg jpg) {
        this.jpg = jpg;
    }

    public Images(Jpg jpg) {
        this.jpg = jpg;
    }
}
