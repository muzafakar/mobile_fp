package com.zulfakarelzaf.authenticjogja.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {

    private int id;
    private String name;
    private String description;
    private String address;
    private double latitude;
    private double longitude;
    private String date;
    private ArrayList<Trivia> trivia;
    private ImageUrl image;
    @SerializedName("group_month")
    private GroupMonth groupMonth;

    public Event(int id, String name, String description, String address, double latitude, double longitude, String date, ArrayList<Trivia> trivia, ImageUrl image, GroupMonth groupMonth) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.trivia = trivia;
        this.image = image;
        this.groupMonth = groupMonth;
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

    public String getDate() {
        return date;
    }

    public ArrayList<Trivia> getTrivia() {
        return trivia;
    }

    public ImageUrl getImage() {
        return image;
    }

    public GroupMonth getGroupMonth() {
        return groupMonth;
    }
}