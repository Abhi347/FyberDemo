package com.abhi.fyberdemo.models;

/**
 * Created by abhi on 25/10/16.
 */

public class PayoutTime {
    private long amount;
    private String readable;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}
