package com.zulfakarelzaf.authenticjogja.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

public class CategoryFood implements Serializable {
    private int id;
    private String name;
    @SerializedName("image")
    private ImageUrl imageUrl;

    public CategoryFood(int id, String name, ImageUrl imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }
}