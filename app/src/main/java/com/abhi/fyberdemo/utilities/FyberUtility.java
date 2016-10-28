package com.abhi.fyberdemo.utilities;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by abhi on 26/10/16.
 */

public class FyberUtility {

    //generates a HashMap for the parameters
    public static HashMap<String,String> getOffersHashMap(String appId,
                                                          String googleAdId,
                                                          String pub0,
                                                          String uid,
                                                          String osVersion,
                                                          String locale,
                                                          long unixTime,
                                                          boolean isLimitAdTrackingEnabled){
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", appId);
        paramsMap.put("format", "json");
        paramsMap.put("google_ad_id", googleAdId);
        paramsMap.put("google_ad_id_limited_tracking_enabled", String.valueOf(isLimitAdTrackingEnabled));
        paramsMap.put("ip", "109.235.143.113");
        paramsMap.put("locale", locale);
        paramsMap.put("offer_types", "112");
        paramsMap.put("os_version", osVersion);
        paramsMap.put("pub0", pub0);
        paramsMap.put("timestamp", String.valueOf(unixTime));
        paramsMap.put("uid", uid);
        return paramsMap;
    }

    //Sorts the HashMap in ascending order of its keys
    public static String getSortedParams(HashMap<String, String> paramsMap) {
        ArrayList<String> sortedKeys = new ArrayList<>(paramsMap.keySet());
        Collections.sort(sortedKeys);

        StringBuilder _stringBuilder = new StringBuilder();
        for (String key : sortedKeys) {
            if (_stringBuilder.length() <= 0) {
                _stringBuilder.append(key).append("=").append(paramsMap.get(key));
            }else{
                _stringBuilder.append("&").append(key).append("=").append(paramsMap.get(key));
            }
        }
        return _stringBuilder.toString();
    }

    //get SHA1 hash of concatenated value of sortedParams and apiKey
    public static String getHashKey(String sortedParams, String apiKey) {
        String preHashString = sortedParams + "&" + apiKey;
        final HashCode hashCode = Hashing.sha1().hashString(preHashString, Charset.defaultCharset());
        return hashCode.toString();
    }

    //validates the OkHttp Response using response signature and apiKey
    public static boolean validateResponse(String responseBody, String responseSignature, String apiKey) throws IOException {
        String preHashString = responseBody+apiKey;
        final HashCode hashCode = Hashing.sha1().hashString(preHashString, Charset.defaultCharset());
        return responseSignature.equalsIgnoreCase(hashCode.toString());
    }
}
