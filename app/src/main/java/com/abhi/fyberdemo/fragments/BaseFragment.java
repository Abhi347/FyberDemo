package com.abhi.fyberdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhi.fyberdemo.listeners.FragmentListener;

import butterknife.ButterKnife;

/**
 * Created by abhi on 25/10/16.
 */

public abstract class BaseFragment extends Fragment {
    private View mParent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mParent = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mParent);
        setupUI(mParent);
        return mParent;
    }

    protected abstract void setupUI(View parent);

    protected abstract int getLayoutId();

    public abstract FragmentListener getFragmentListener();

    public abstract void setFragmentListener(FragmentListener fragmentListener);
}
