package com.abhi.fyberdemo.fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhi.fyberdemo.R;
import com.abhi.fyberdemo.adapters.OffersAdapter;
import com.abhi.fyberdemo.listeners.FragmentListener;
import com.abhi.fyberdemo.listeners.OfferClickListener;
import com.abhi.fyberdemo.models.OfferModel;
import com.abhi.fyberdemo.models.OfferResponse;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OffersFragment extends BaseFragment {
    private FragmentListener mFragmentListener;
    private OfferResponse mOfferResponse;

    @BindView(R.id.list_offers)
    protected RecyclerView mOffersRecyclerView;

    @BindView(R.id.text_no_offers)
    TextView mNoOffersTextView;

    private OffersAdapter mOfferAdapter;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mOffersRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mOfferAdapter = new OffersAdapter();
        mOfferAdapter.setOfferClickListener(new OfferClickListener() {
            @Override
            public void OnOfferClick(View view, OfferModel offer) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(offer.getLink()));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        mOffersRecyclerView.setAdapter(mOfferAdapter);
        if (mOfferResponse != null && mOfferResponse.getOffers() != null && mOfferResponse.getOffers().length > 0) {
            mOfferAdapter.setOfferModels(mOfferResponse.getOffers());
            mNoOffersTextView.setVisibility(View.GONE);
            mOffersRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mNoOffersTextView.setVisibility(View.VISIBLE);
            mOffersRecyclerView.setVisibility(View.GONE);
        }
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
