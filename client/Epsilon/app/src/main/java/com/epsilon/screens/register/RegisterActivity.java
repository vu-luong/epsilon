package com.epsilon.screens.register;

import android.os.Bundle;

import com.epsilon.R;
import com.epsilon.commons.GenericActivity;

/**
 * Created by Dandoh on 4/9/16.
 */
public class RegisterActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);


        initFragment(RegisterFragment.getInstance());
    }
}
