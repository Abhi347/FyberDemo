package com.abhi.fyberdemo.viewmodels;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fyberdemo.R;
import com.abhi.fyberdemo.listeners.OfferClickListener;
import com.abhi.fyberdemo.models.OfferModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abhi on 25/10/16.
 */

public class OfferViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_thumbnail)
    protected ImageView mThumbnailImageView;

    @BindView(R.id.text_title)
    protected TextView mTitleTextView;

    @BindView(R.id.text_action)
    protected TextView mActionTextView;
    private OfferModel mOfferModel;
    private OfferClickListener mListener;


    public OfferViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnOfferClick(itemView, mOfferModel);
            }
        });
    }

    public void setModel(OfferModel offer) {
        mOfferModel = offer;
        Picasso
                .with(mThumbnailImageView.getContext())
                .load(mOfferModel.getThumbnail().getLowRes())
                .into(mThumbnailImageView);
        mTitleTextView.setText(mOfferModel.getTitle());
        mActionTextView.setText(mOfferModel.getRequiredActions());
    }

    public void setListener(OfferClickListener listener) {
        mListener = listener;
    }
}
