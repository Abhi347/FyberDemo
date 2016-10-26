package com.abhi.fyberdemo;

import com.abhi.fyberdemo.utilities.FyberUtility;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FyberUtilityUnitTest {
    @Test
    public void testConvertParamsToSHA1Hash() {
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
        assertEquals("Parameters Sorting failed", paramsExpected, params);

        String hashString = FyberUtility.getHashKey(params, "123456789");
        String hashStringExpected = "d79dd6aef01987ffd5beaea9ce43103d729f3f64";
        assertEquals("Parameters conversion to SHA1 hash failed", hashStringExpected,hashString);
    }
}