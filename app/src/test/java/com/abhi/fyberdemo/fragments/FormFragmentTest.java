package com.abhi.fyberdemo.fragments;

import android.support.v4.app.Fragment;

import com.abhi.fyberdemo.MainActivity;
import com.abhi.fyberdemo.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

/**
 * Created by abhi on 28/10/16.
 */
public class FormFragmentTest {
    private MainActivity mMainActivity;
    private FormFragment mFormFragment;
    @Before
    public void setUp() throws Exception {
        mMainActivity = Robolectric.setupActivity(MainActivity.class);
        mFormFragment = FormFragment.newInstance(mMainActivity);
        startFragment(mFormFragment);
    }
    private void startFragment(Fragment fragment) {
        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frag_container, fragment)
                .commit();
    }
    @Test
    public void newInstance() throws Exception {
        Assert.assertNotNull(mMainActivity);
        Assert.assertNotNull(mFormFragment);
        Assert.assertNotNull(mFormFragment.getFragmentListener());
    }

    @Test
    public void getLayoutId() throws Exception {
        int layoutId = mFormFragment.getLayoutId();
        Assert.assertTrue("Layout Id can not be 0 ", layoutId != 0);
        Assert.assertTrue("Layout Id can not be negative ", layoutId > 0);
    }
}