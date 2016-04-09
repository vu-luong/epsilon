package com.epsilon.commons;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.epsilon.utils.Utils;

import butterknife.ButterKnife;

public class GenericRetainedFragment extends GenericFragment {

    private ProgressDialog mProgressDialog;
    protected boolean isProcessing;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.log(TAG, "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(getActivity());

        if (isProcessing) {
            showLoading();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mProgressDialog = null;
        ButterKnife.unbind(this);
    }

    protected void showLoading() {

        isProcessing = true;
        if (mProgressDialog == null || mProgressDialog.isShowing())
            return;

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setTitle("Processing");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Please wait ...");
        mProgressDialog.show();
    }

    protected void dismissLoading(){
        isProcessing = false;
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    public void showProcessingIndicator(boolean isProcessing) {

        if (isProcessing) {
            showLoading();
        } else {
            dismissLoading();
        }
    }

}
