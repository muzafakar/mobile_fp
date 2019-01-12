package com.zulfakarelzaf.authenticjogja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Craft implements Serializable {

    private int id;
    private String name;
    private String description;
    private String address;
    private ImageUrl image;
    private double latitude;
    private double longitude;
    private ArrayList<Trivia> trivia;

    public Craft(int id, String name, String description, String address, ImageUrl image, double latitude, double longitude, ArrayList<Trivia> trivia) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trivia = trivia;
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

    public ImageUrl getImage() {
        return image;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<Trivia> getTrivia() {
        return trivia;
    }
}
