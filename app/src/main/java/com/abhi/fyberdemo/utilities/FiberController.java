package com.abhi.fyberdemo.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.abhi.fyberdemo.listeners.AdIdCallback;
import com.abhi.fyberdemo.listeners.OfferListener;
import com.abhi.fyberdemo.models.OfferResponse;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;
import com.noob.lumberjack.LumberJack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by abhi on 24/10/16.
 */

public class FiberController {

    public static final String BASE_OFFERS_URL = "http://api.fyber.com/feed/v1/offers.json";

    private Context mContext;
    private String mGoogleAdId = null;
    private boolean mIsLimitAdTrackingEnabled = false;
    private boolean mIsAdIdRetrievalPending = false;

    //region singleton
    private static FiberController mInstance;

    public static FiberController getInstance() {
        if (mInstance == null) {
            mInstance = new FiberController();
        }
        return mInstance;
    }

    private FiberController() {
    }
    //endregion

    public void init(Context context) {
        mContext = context;
        //getAdvertisingData(null);
    }

    private void getAdvertisingData(final AdIdCallback callback) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                mIsAdIdRetrievalPending = true;
            }

            @Override
            protected Void doInBackground(Void... voidsParam) {
                try {
                    AdvertisingIdClient.Info adClientInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext);
                    mGoogleAdId = adClientInfo.getId();
                    mIsLimitAdTrackingEnabled = adClientInfo.isLimitAdTrackingEnabled();
                    if(callback!=null){
                        callback.onAdIdReceived(mGoogleAdId,mIsLimitAdTrackingEnabled);
                    }
                } catch (IOException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException eParam) {
                    eParam.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void voidParam) {
                mIsAdIdRetrievalPending = false;
            }
        }.execute();
    }

    public void getOfferWall(final String uid, final String apiKey, final String appId, final String pub0, final OfferListener offerListener) {
        if(mGoogleAdId==null && !mIsAdIdRetrievalPending){
            getAdvertisingData(new AdIdCallback() {
                @Override
                public void onAdIdReceived(String adId, boolean isLimitAdTrackingEnabled) {
                    getOfferWall(uid,apiKey,appId,pub0, offerListener);
                }
            });
            return;
        }
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voidsParam) {
                String response = null;
                try {
                    response = fetchData(uid, apiKey, appId, pub0);
                } catch (IOException eParam) {
                    eParam.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                LumberJack.d("FiberController", response);
                Gson gson = new Gson();
                OfferResponse offerResponse = gson.fromJson(response,OfferResponse.class);
                offerListener.onOfferReceived(offerResponse);
            }
        }.execute();
    }

    private String fetchData(String uid, String apiKey, String appId, String pub0) throws IOException {
        OkHttpClient client = new OkHttpClient();

        long unixTime = System.currentTimeMillis() / 1000L;
        String locale = Locale.getDefault().getLanguage().toUpperCase();
        String osVersion = Build.VERSION.RELEASE;
        HashMap<String, String> paramsMap = FyberUtility.getOffersHashMap(
                appId,
                mGoogleAdId,
                pub0,
                uid,
                osVersion,
                locale,
                unixTime,
                mIsLimitAdTrackingEnabled);

        String params = FyberUtility.getSortedParams(paramsMap);
        String hashString = FyberUtility.getHashKey(params,apiKey);

        String url = BASE_OFFERS_URL + "?" + params + "&hashkey=" + hashString;
        LumberJack.d("FiberController", "url = " + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
