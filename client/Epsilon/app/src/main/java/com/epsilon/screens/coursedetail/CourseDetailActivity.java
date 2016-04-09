package com.epsilon.screens.coursedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.epsilon.R;
import com.epsilon.commons.GenericActivity;
import com.epsilon.screens.categorydetail.CategoryDetailActivity;
import com.epsilon.screens.categorydetail.CategoryDetailFragment;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CourseDetailActivity extends GenericActivity {

    private static final String COURSE_ID_KEY = "course id key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        initFragment(CourseDetailFragment.getInstance(getIntent().getIntExtra(COURSE_ID_KEY, 1)));
    }

    public static Intent makeIntent(Context context, int courseId) {
        return new Intent(context, CourseDetailActivity.class)
                .putExtra(COURSE_ID_KEY, courseId);
    }
}
