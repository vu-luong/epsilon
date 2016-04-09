package com.epsilon.screens.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epsilon.R;
import com.epsilon.commons.GenericRetainedFragment;
import com.epsilon.commons.GenericRetainedToolbarFragment;

/**
 * Created by Dandoh on 4/9/16.
 */
public class RegisterFragment extends GenericRetainedToolbarFragment {

    public static Fragment getInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
