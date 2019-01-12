package com.zulfakarelzaf.authenticjogja.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Culinaries implements Serializable {


    private int id;
    private String name;
    private String description;
    private String address;
    private double latitude;
    private double longitude;
    @SerializedName("image")
    private ImageUrl imageUrl;
    private ArrayList<Trivia> trivia;
    @SerializedName("category_food")
    private CategoryFood categoryFood;

    public Culinaries(int id, String name, String description, String address, double latitude, double longitude, ImageUrl imageUrl, ArrayList<Trivia> trivia, CategoryFood categoryFood) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.trivia = trivia;
        this.categoryFood = categoryFood;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    public ArrayList<Trivia> getTrivia() {
        return trivia;
    }

    public CategoryFood getCategoryFood() {
        return categoryFood;
    }
}