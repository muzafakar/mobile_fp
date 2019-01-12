package com.zulfakarelzaf.authenticjogja.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zulfakar on 06/07/18.
 * For educational purposes
 */

public class TrendingItem implements Serializable {
    private int id;
    @SerializedName("craft")
    private int craftId;
    @SerializedName("culinary")
    private int culinaryId;
    @SerializedName("event")
    private int eventId;

    public TrendingItem(int id, int craftId, int culinaryId, int eventId) {
        this.id = id;
        this.craftId = craftId;
        this.culinaryId = culinaryId;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public int getCraftId() {
        return craftId;
    }

    public int getCulinaryId() {
        return culinaryId;
    }

    public int getEventId() {
        return eventId;
    }
}