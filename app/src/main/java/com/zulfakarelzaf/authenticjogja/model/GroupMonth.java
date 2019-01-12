package com.zulfakarelzaf.authenticjogja.model;

import java.io.Serializable;

public class GroupMonth implements Serializable
{
    public int id;
    public String name;

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
