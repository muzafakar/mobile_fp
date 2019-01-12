package com.zulfakarelzaf.authenticjogja.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryLanguages implements Serializable {

    private int id;
    private String name;

    public CategoryLanguages(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}