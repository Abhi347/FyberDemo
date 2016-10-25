package com.abhi.fyberdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhi.fyberdemo.R;
import com.abhi.fyberdemo.listeners.OfferClickListener;
import com.abhi.fyberdemo.models.OfferModel;
import com.abhi.fyberdemo.viewmodels.OfferViewHolder;

/**
 * Created by abhi on 25/10/16.
 */

public class OffersAdapter extends RecyclerView.Adapter<OfferViewHolder> {
    private OfferModel[] mOfferModels;

    private OfferClickListener mOfferClickListener;

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offer, parent, false);
        OfferViewHolder _viewHolder = new OfferViewHolder(_view);
        if(mOfferClickListener!=null){
            _viewHolder.setListener(mOfferClickListener);
        }
        return _viewHolder;
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        holder.setModel(mOfferModels[position]);
    }

    @Override
    public int getItemCount() {
        if (mOfferModels == null)
            return 0;
        return mOfferModels.length;
    }

    //region Accessors
    public OfferModel[] getOfferModels() {
        return mOfferModels;
    }

    public void setOfferModels(OfferModel[] offerModels) {
        mOfferModels = offerModels;
    }

    public OfferClickListener getOfferClickListener() {
        return mOfferClickListener;
    }

    public void setOfferClickListener(OfferClickListener offerClickListener) {
        mOfferClickListener = offerClickListener;
    }
    //endregion
}
