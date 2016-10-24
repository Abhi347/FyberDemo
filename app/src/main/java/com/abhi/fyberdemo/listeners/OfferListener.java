package com.abhi.fyberdemo.listeners;

import com.abhi.fyberdemo.models.OfferResponse;

/**
 * Created by abhi on 25/10/16.
 */

public interface OfferListener {
    void onOfferReceived(OfferResponse response);
}
