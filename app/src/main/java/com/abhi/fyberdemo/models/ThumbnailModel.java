package com.abhi.fyberdemo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhi on 25/10/16.
 */

public class ThumbnailModel {
    @SerializedName("lowres")
    private String lowRes;

    @SerializedName("highres")
    private String highRes;

    public String getLowRes() {
        return lowRes;
    }

    public void setLowRes(String lowRes) {
        this.lowRes = lowRes;
    }

    public String getHighRes() {
        return highRes;
    }

    public void setHighRes(String highRes) {
        this.highRes = highRes;
    }
}
