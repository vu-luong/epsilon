package com.epsilon.screens.categorydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.ActionMenuView;
import android.view.MenuItem;

import com.epsilon.R;
import com.epsilon.commons.GenericActivity;
import com.epsilon.screens.login.LoginFragment;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CategoryDetailActivity extends GenericActivity {
    private static final String CATEGORY_ID_KEY = "category id";
    private static final String CATEGORY_NAME_KEY = "category name key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        if (savedInstanceState == null)
            initFragment(CategoryDetailFragment.getInstance(getIntent().getIntExtra(CATEGORY_ID_KEY, 1)));

        setTitle(getIntent().getStringExtra(CATEGORY_NAME_KEY));
    }

    public static Intent makeIntent(Context context, int categoryId, String name) {
        return new Intent(context, CategoryDetailActivity.class)
                .putExtra(CATEGORY_ID_KEY, categoryId)
                .putExtra(CATEGORY_NAME_KEY, name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
