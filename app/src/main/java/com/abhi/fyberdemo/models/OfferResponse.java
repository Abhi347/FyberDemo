package com.abhi.fyberdemo.models;

/**
 * Created by abhi on 25/10/16.
 */

public class OfferResponse {
    private String code;
    private String message;
    private int count;
    private int pages;
    private InformationModel information;
    private OfferModel[] offers;
    private transient String signature;
    private transient boolean isValidResponse;

    public boolean containsOffers(){
        return getOffers() != null && getOffers().length > 0;
    }

    //region Accessors

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public InformationModel getInformation() {
        return information;
    }

    public void setInformation(InformationModel information) {
        this.information = information;
    }

    public OfferModel[] getOffers() {
        return offers;
    }

    public void setOffers(OfferModel[] offers) {
        this.offers = offers;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isValidResponse() {
        return isValidResponse;
    }

    public void setValidResponse(boolean validResponse) {
        isValidResponse = validResponse;
    }

    //endregion
}
