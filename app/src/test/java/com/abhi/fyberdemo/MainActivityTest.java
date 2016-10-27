package com.abhi.fyberdemo;

import android.support.v4.app.Fragment;
import android.view.View;

import com.abhi.fyberdemo.fragments.FormFragment;
import com.abhi.fyberdemo.fragments.OffersFragment;
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
public class MainActivityTest {
    private MainActivity mMainActivity;

    @Before
    public void setUp() throws Exception {
        mMainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void showFormFragment() throws Exception {
        mMainActivity.showFormFragment();
        Fragment _fragment = mMainActivity.getSupportFragmentManager().findFragmentById(R.id.frag_container);
        Assert.assertTrue("FormFragment is not displayed",_fragment.getClass()== FormFragment.class);
    }

    @Test
    public void showOffersFragment() throws Exception {
        OfferResponse _offerResponse = Mockito.mock(OfferResponse.class);
        mMainActivity.showOffersFragment(_offerResponse);
        Fragment _fragment = mMainActivity.getSupportFragmentManager().findFragmentById(R.id.frag_container);
        Assert.assertTrue("FormFragment is not displayed",_fragment.getClass()== OffersFragment.class);
    }

    @Test
    public void onOfferReceived() throws Exception {
        OfferResponse _offerResponse = Mockito.mock(OfferResponse.class);
        mMainActivity.onOfferReceived(_offerResponse);
        Fragment _fragment = mMainActivity.getSupportFragmentManager().findFragmentById(R.id.frag_container);
        Assert.assertTrue("FormFragment is not displayed",_fragment.getClass()== OffersFragment.class);
    }

    @Test
    public void onShowProgress() throws Exception {
        mMainActivity.onShowProgress();
        Assert.assertTrue("ProgressBar is not displayed",mMainActivity.mProgressBar.getVisibility()== View.VISIBLE);
    }

    @Test
    public void onHideProgress() throws Exception {
        mMainActivity.onHideProgress();
        Assert.assertTrue("ProgressBar is not hidden",mMainActivity.mProgressBar.getVisibility()== View.GONE);
    }

}