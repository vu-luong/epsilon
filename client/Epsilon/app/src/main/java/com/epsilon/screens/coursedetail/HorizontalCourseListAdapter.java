package com.epsilon.screens.coursedetail;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epsilon.R;
import com.epsilon.screens.categorydetail.CourseListAdapter;

public class HorizontalCourseListAdapter extends CourseListAdapter {

    private static final String TAG = "CourseListAdapter";

    public HorizontalCourseListAdapter(OnCourseItemClick onCourseItemClick) {
        super(onCourseItemClick);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
//         create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_fixed_size, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

}
