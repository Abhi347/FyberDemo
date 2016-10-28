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

//Base class for all the fragments in the project
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _parent = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, _parent);
        setupUI(_parent);
        return _parent;
    }

    protected abstract void setupUI(View parent);

    protected abstract int getLayoutId();

    public abstract FragmentListener getFragmentListener();

    public abstract void setFragmentListener(FragmentListener fragmentListener);
}
