package com.abhi.fyberdemo.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.abhi.fyberdemo.R;
import com.abhi.fyberdemo.listeners.FragmentListener;
import com.abhi.fyberdemo.listeners.OfferListener;
import com.abhi.fyberdemo.models.OfferResponse;
import com.abhi.fyberdemo.utilities.FiberController;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends BaseFragment {
    @BindView(R.id.edit_uid)
    protected EditText mUidEditText;
    @BindView(R.id.edit_api_key)
    protected EditText mApiKeyEditText;
    @BindView(R.id.edit_app_id)
    protected EditText mAppIdEditText;
    @BindView(R.id.edit_pub0)
    protected EditText mPub0EditText;

    private FragmentListener mFragmentListener;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FormFragment.
     */
    public static FormFragment newInstance(FragmentListener listener) {
        FormFragment _fragment = new FormFragment();
        _fragment.setFragmentListener(listener);
        return _fragment;
    }

    @Override
    protected void setupUI(View parent) {
        //Turn off for production builds
        //AutoFillUtility.fillFormData(mUidEditText,mApiKeyEditText,mAppIdEditText,mPub0EditText);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_form;
    }

    @OnClick(R.id.button_get_data)
    public void onGetDataClick(final View view) {
        String uid = mUidEditText.getText().toString();
        String apiKey = mApiKeyEditText.getText().toString();
        String appId = mAppIdEditText.getText().toString();
        String pub0 = mPub0EditText.getText().toString();

        //Ensure the data is not empty
        if (uid.isEmpty() || apiKey.isEmpty() || appId.isEmpty() || pub0.isEmpty()) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage("All fields are necessary")
                    .setNeutralButton("OK", null)
                    .create()
                    .show();
            return;
        }
        view.setEnabled(false);
        getFragmentListener().onShowProgress();

        FiberController.getInstance().getOfferWall(uid, apiKey, appId, pub0, new OfferListener() {
            @Override
            public void onOfferReceived(OfferResponse response) {
                getFragmentListener().onOfferReceived(response);
                getFragmentListener().onHideProgress();
                view.setEnabled(true);
            }
        });
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
    //endregion
}
