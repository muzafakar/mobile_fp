package com.zulfakarelzaf.authenticjogja.model;

import java.io.Serializable;

/**
 * Created by XAgusart on 5/30/2018.
 */

public class ItemModel implements Serializable {
    private String url;
    private String title;


    public ItemModel(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
