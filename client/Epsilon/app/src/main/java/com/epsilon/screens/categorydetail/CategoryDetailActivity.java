package com.epsilon.screens.categorydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.epsilon.R;
import com.epsilon.commons.GenericActivity;
import com.epsilon.screens.login.LoginFragment;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CategoryDetailActivity extends GenericActivity {
    private static final String CATEGORY_ID_KEY = "category id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        if (savedInstanceState == null)
            initFragment(CategoryDetailFragment.getInstance(getIntent().getIntExtra(CATEGORY_ID_KEY, 1)));
    }

    public static Intent makeIntent(Context context, int categoryId) {
        return new Intent(context, CategoryDetailActivity.class)
                .putExtra(CATEGORY_ID_KEY, categoryId);
    }
}
