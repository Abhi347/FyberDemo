package com.abhi.fyberdemo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhi on 25/10/16.
 */

public class OfferModel {

    private String title;

    @SerializedName("offer_id")
    private String offerId;

    private String teaser;

    @SerializedName("required_actions")
    private String requiredActions;

    private String link;

    @SerializedName("offer_types")
    private OfferType[] offerTypes;

    private int payout;

    @SerializedName("time_to_payout")
    private PayoutTime timeToPayout;

    private ThumbnailModel thumbnail;

    @SerializedName("store_id")
    private String storeId;

    //region Accessors
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public OfferType[] getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(OfferType[] offerTypes) {
        this.offerTypes = offerTypes;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public PayoutTime getTimeToPayout() {
        return timeToPayout;
    }

    public void setTimeToPayout(PayoutTime timeToPayout) {
        this.timeToPayout = timeToPayout;
    }

    public ThumbnailModel getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailModel thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    //endregion
}
