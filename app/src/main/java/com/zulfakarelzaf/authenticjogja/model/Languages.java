package com.zulfakarelzaf.authenticjogja.model;

import java.io.Serializable;

public class Languages implements Serializable {

    public int id;
    private String us;
    private String indonesia;
    private String javanese;
    private CategoryLanguages category_language;

    public Languages(int id, String us, String indonesia, String javanese, CategoryLanguages category_language) {
        this.id = id;
        this.us = us;
        this.indonesia = indonesia;
        this.javanese = javanese;
        this.category_language = category_language;
    }

    public int getId() {
        return id;
    }

    public String getUs() {
        return us;
    }

    public String getIndonesia() {
        return indonesia;
    }

    public String getJavanese() {
        return javanese;
    }

    public CategoryLanguages getCategory_language() {
        return category_language;
    }
}