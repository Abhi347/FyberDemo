package com.abhi.fyberdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abhi.fyberdemo.fragments.FormFragment;
import com.abhi.fyberdemo.fragments.OffersFragment;
import com.abhi.fyberdemo.listeners.FragmentListener;
import com.abhi.fyberdemo.models.OfferResponse;
import com.abhi.fyberdemo.utilities.FiberController;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentListener{
    FormFragment mFormFragment;
    OffersFragment mOffersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FiberController.getInstance().init(this);

        showFormFragment();
    }

    public void showFormFragment() {
        if (mFormFragment == null) {
            mFormFragment = FormFragment.newInstance(this);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, mFormFragment)
                .commit();
    }

    public void showOffersFragment(OfferResponse offerResponse) {
        if (mOffersFragment == null) {
            mOffersFragment = OffersFragment.newInstance(this, offerResponse);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, mOffersFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onOfferReceived(OfferResponse response) {
        showOffersFragment(response);
    }
}