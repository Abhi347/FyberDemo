package com.abhi.fyberdemo.fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fyberdemo.R;
import com.abhi.fyberdemo.models.OfferModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OfferDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OfferDialogFragment extends DialogFragment {
    private OfferModel mOffer;

    @BindView(R.id.image_thumbnail)
    protected ImageView mOfferImageView;

    @BindView(R.id.text_title)
    protected TextView mOfferTitleTextView;

    @BindView(R.id.text_action)
    protected TextView mOfferActionTextView;

    @BindView(R.id.button_earn_coins)
    protected Button mEarnCoinButton;


    public OfferDialogFragment() {
        // Required empty public constructor
    }

    public static OfferDialogFragment newInstance(OfferModel offer) {
        OfferDialogFragment fragment = new OfferDialogFragment();
        fragment.setOffer(offer);
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Dialog_NoActionBar);
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_offer_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        ButterKnife.bind(this, parent);

        loadOffer();
        return parent;
    }

    /*@Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        return dialog;
    }*/

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }*/

    private void loadOffer() {
        if (mOfferTitleTextView == null || mOfferActionTextView == null || mEarnCoinButton == null || mOfferImageView == null) {
            throw new IllegalStateException("OfferDialogFragment is not initialised yet");
        }
        mOfferTitleTextView.setText(mOffer.getTitle());
        mOfferActionTextView.setText(mOffer.getTeaser());
        mEarnCoinButton.setText("Earn " + mOffer.getPayout() + " VCS Coins");
        Picasso
                .with(mOfferImageView.getContext())
                .load(mOffer.getThumbnail().getHighRes())
                .into(mOfferImageView);
    }

    @OnClick(R.id.button_earn_coins)
    public void onEarnCoinClick() {
        try {
            //Open browser for offer link directly. Could have used a popup to show first.
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mOffer.getLink()));
            startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {
            //In case the device does not have a browser capable of opening this link. (Better safe than sorry!)
            e.printStackTrace();
        }
    }

    @OnClick(R.id.button_close_offer)
    public void onOfferCloseClick() {
        mOffer = null;
        this.dismiss();
    }

    public OfferModel getOffer() {
        return mOffer;
    }

    public void setOffer(OfferModel offer) {
        mOffer = offer;
    }
}
