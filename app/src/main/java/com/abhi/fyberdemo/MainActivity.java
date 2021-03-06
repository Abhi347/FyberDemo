package com.abhi.fyberdemo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.abhi.fyberdemo.fragments.FormFragment;
import com.abhi.fyberdemo.fragments.OffersFragment;
import com.abhi.fyberdemo.listeners.FragmentListener;
import com.abhi.fyberdemo.models.OfferResponse;
import com.abhi.fyberdemo.utilities.FiberController;
import com.noob.lumberjack.LogLevel;
import com.noob.lumberjack.LumberJack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private FormFragment mFormFragment;
    private OffersFragment mOffersFragment;

    @BindView(R.id.progress)
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (!BuildConfig.DEBUG) {
            //Turn Off LumberJack logs on Release builds
            LumberJack.setLogLevel(LogLevel.None);
        }

        //Instantiate FiberController with the Activity context
        FiberController.getInstance().init(this);

        onHideProgress();
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
        String errorMessage = null;
        if (response == null) {
            errorMessage = "Didn't receive any valid response";
        } else if (response.getSignature() == null) {
            errorMessage = "Didn't receive Response signature";
        } else if (!response.isValidResponse()) {
            errorMessage = "Response signature is not valid";
        } else {
            showOffersFragment(response);
        }

        if (errorMessage != null) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(errorMessage)
                    .setNeutralButton("OK", null)
                    .create()
                    .show();
        }
    }

    @Override
    public void onShowProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }
}