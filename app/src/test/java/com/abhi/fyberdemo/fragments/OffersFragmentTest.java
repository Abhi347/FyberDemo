package com.abhi.fyberdemo.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import com.abhi.fyberdemo.BuildConfig;
import com.abhi.fyberdemo.MainActivity;
import com.abhi.fyberdemo.R;
import com.abhi.fyberdemo.models.OfferResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by abhi on 28/10/16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class OffersFragmentTest {
    private MainActivity mMainActivity;
    private OffersFragment mOffersFragment;

    @Before
    public void setUp() throws Exception {
        OfferResponse _offerResponse = Mockito.mock(OfferResponse.class);
        mMainActivity = Robolectric.setupActivity(MainActivity.class);
        mOffersFragment = OffersFragment.newInstance(mMainActivity, _offerResponse);
        startFragment(mOffersFragment);
    }

    private void startFragment(Fragment fragment) {
        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frag_container, fragment)
                .commit();
    }

    @Test
    public void newInstance() throws Exception {
        /*OfferResponse _offerResponse = Mockito.mock(OfferResponse.class);
        MainActivity _mainActivity = Mockito.mock(MainActivity.class);
        OffersFragment _offersFragment = OffersFragment.newInstance(_mainActivity,_offerResponse);*/
        Assert.assertNotNull(mOffersFragment);
        Assert.assertNotNull(mMainActivity);
    }

    @Test
    public void setupUI() throws Exception {

    }

    @Test
    public void loadOffers() throws Exception {
        OfferResponse _offerResponse = mOffersFragment.getOfferResponse();
        Mockito.when(_offerResponse.containsOffers()).thenReturn(false);
        mOffersFragment.loadOffers(_offerResponse);
        Assert.assertEquals("No Offers Test is not displayed when there are no offers", mOffersFragment.mNoOffersTextView.getVisibility(), View.VISIBLE);
        Assert.assertEquals("RecyclerView is displayed when there are no offers", mOffersFragment.mOffersRecyclerView.getVisibility(), View.GONE);

        Mockito.when(_offerResponse.containsOffers()).thenReturn(true);
        mOffersFragment.loadOffers(_offerResponse);
        Assert.assertEquals("No Offers Test is displayed when there are offers", mOffersFragment.mNoOffersTextView.getVisibility(), View.GONE);
        Assert.assertEquals("RecyclerView is not displayed when there are offers", mOffersFragment.mOffersRecyclerView.getVisibility(), View.VISIBLE);
    }

    @Test
    public void getLayoutId() throws Exception {
        int layoutId = mOffersFragment.getLayoutId();
        Assert.assertTrue("Layout Id can not be 0 ", layoutId != 0);
        Assert.assertTrue("Layout Id can not be negative ", layoutId > 0);
    }
}