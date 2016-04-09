package com.epsilon.screens.coursedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epsilon.commons.GenericRetainedToolbarFragment;
import com.epsilon.models.entities.Course;


/**
 * Created by AnhVu on 4/9/16.
 */
public class CourseDetailFragment extends GenericRetainedToolbarFragment implements CourseDetailContract.View{

    private static final String COURSE_ID = "course id";

    public static Fragment getInstance(int courseId) {
        Bundle bundle = new Bundle();
        bundle.putInt(COURSE_ID, courseId);

        Fragment fragment = new CourseDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void displayCourseDetail(Course course) {

    }

    @Override
    public void goToCourseUrl(String url) {

    }

    @Override
    public void onError(String error) {

    }
}
