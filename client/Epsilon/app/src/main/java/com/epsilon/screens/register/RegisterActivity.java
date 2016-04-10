package com.epsilon.screens.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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
        if (savedInstanceState == null)
            initFragment(RegisterFragment.getInstance());
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }
}
