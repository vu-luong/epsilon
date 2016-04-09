package com.epsilon.screens.category;

import android.os.Bundle;

import com.epsilon.R;
import com.epsilon.commons.GenericActivity;
import com.epsilon.screens.login.LoginFragment;

/**
 * Created by Dandoh on 4/9/16.
 */
public class CategoryActivity extends GenericActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);


        initFragment(LoginFragment.getInstace());
    }
}
