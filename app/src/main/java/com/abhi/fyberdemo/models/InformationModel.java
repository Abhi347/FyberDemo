package com.abhi.fyberdemo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhi on 25/10/16.
 */

public class InformationModel {
    @SerializedName("app_name")
    private String appName;

    @SerializedName("appid")
    private String appId;

    @SerializedName("virtual_currency")
    private String virtualCurrency;

    @SerializedName("virtual_currency_sale_enabled")
    private boolean isVirtualCurrencySaleEnabled;

    private String country;

    private String language;

    @SerializedName("support_url")
    private String supportUrl;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public boolean isVirtualCurrencySaleEnabled() {
        return isVirtualCurrencySaleEnabled;
    }

    public void setVirtualCurrencySaleEnabled(boolean virtualCurrencySaleEnabled) {
        isVirtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }
}
