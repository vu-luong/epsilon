package com.epsilon.screens.login;

import android.os.Bundle;

import com.epsilon.R;
import com.epsilon.commons.GenericActivity;

/**
 * Created by Dandoh on 4/9/16.
 */
public class LoginActivity extends GenericActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);


        initFragment(LoginFragment.getInstace());
    }
}
