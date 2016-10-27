package com.abhi.fyberdemo.utilities;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by abhi on 28/10/16.
 */
public class FyberUtilityTest {
    @Test
    public void getOffersHashMap() throws Exception {
        HashMap<String, String> paramsMap = FyberUtility.getOffersHashMap("1234",
                "12b2",
                "MainActivity",
                "batguy",
                "4.1.2",
                "EN",
                1477465419L,
                false);
        Assert.assertNotNull(paramsMap);
    }

    @Test
    public void getSortedParams() throws Exception {
        HashMap<String, String> paramsMap = FyberUtility.getOffersHashMap("1234",
                "12b2",
                "MainActivity",
                "batguy",
                "4.1.2",
                "EN",
                1477465419L,
                false);

        String params = FyberUtility.getSortedParams(paramsMap);
        String paramsExpected = "appid=1234&format=json&google_ad_id=12b2&google_ad_id_limited_tracking_enabled=false&ip=109.235.143.113&locale=EN&offer_types=112&os_version=4.1.2&pub0=MainActivity&timestamp=1477465419&uid=batguy";
        Assert.assertEquals("Parameters Sorting failed", paramsExpected, params);
    }

    @Test
    public void getHashKey() throws Exception {
        String params = "appid=1234&format=json&google_ad_id=12b2&google_ad_id_limited_tracking_enabled=false&ip=109.235.143.113&locale=EN&offer_types=112&os_version=4.1.2&pub0=MainActivity&timestamp=1477465419&uid=batguy";
        String hashString = FyberUtility.getHashKey(params, "123456789");
        String hashStringExpected = "d79dd6aef01987ffd5beaea9ce43103d729f3f64";
        assertEquals("Parameters conversion to SHA1 hash failed", hashStringExpected,hashString);
    }
}