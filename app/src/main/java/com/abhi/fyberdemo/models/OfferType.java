package com.abhi.fyberdemo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhi on 25/10/16.
 */

public class OfferType {

    @SerializedName("offer_type_id")
    private String offerTypeId;
    private String readable;

    public String getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(String offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}
