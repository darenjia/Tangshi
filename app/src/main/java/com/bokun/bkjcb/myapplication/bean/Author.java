package com.bokun.bkjcb.myapplication.bean;

import java.io.Serializable;

/**
 * Created by DengShuai on 2018/2/28.
 * Description :
 */

public class Author implements Serializable{
    int id;
    String name;
    String image;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
