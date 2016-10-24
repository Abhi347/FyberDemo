package com.abhi.fyberdemo.fragments;


import android.support.v4.app.Fragment;
import android.view.View;

import com.abhi.fyberdemo.R;
import com.abhi.fyberdemo.listeners.FragmentListener;
import com.abhi.fyberdemo.models.OfferResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OffersFragment extends BaseFragment {
    private FragmentListener mFragmentListener;
    private OfferResponse mOfferResponse;

    public OffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OffersFragment.
     */
    public static OffersFragment newInstance(FragmentListener listener, OfferResponse offerResponse) {
        OffersFragment _fragment = new OffersFragment();
        _fragment.setFragmentListener(listener);
        _fragment.setOfferResponse(offerResponse);
        return _fragment;
    }

    @Override
    protected void setupUI(View parent) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_offers;
    }

    //region Accessors
    @Override
    public FragmentListener getFragmentListener() {
        return mFragmentListener;
    }

    @Override
    public void setFragmentListener(FragmentListener fragmentListener) {
        mFragmentListener = fragmentListener;
    }

    public void setOfferResponse(OfferResponse offerResponse) {
        mOfferResponse = offerResponse;
    }

    public OfferResponse getOfferResponse() {
        return mOfferResponse;
    }
    //endregion
}
