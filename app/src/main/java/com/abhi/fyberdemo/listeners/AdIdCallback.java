package com.abhi.fyberdemo.listeners;

/**
 * Created by abhi on 25/10/16.
 */

public interface AdIdCallback {
    void onAdIdReceived(String adId, boolean isLimitAdTrackingEnabled);
}
