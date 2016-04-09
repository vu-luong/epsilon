package com.epsilon.screens.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epsilon.screens.category.CategoryFragment;


public class HomeScreenPagerAdapter extends FragmentPagerAdapter {
    public static final int USER_MODE = 214;
    public static final int CARRIER_MODE = 35;

    private final int PAGE_COUNT = 3;
    private String[] mNameTabs;
    private Context context;

    private String TAG = "HomeScreenPagerAdapter";

    public HomeScreenPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        mNameTabs = new String[]{"Đã tham gia", "Khám phá", "Gợi ý" };
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return new CategoryFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNameTabs[position];
    }
}