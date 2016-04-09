package com.epsilon.screens.categorydetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epsilon.R;
import com.epsilon.commons.GenericRetainedToolbarFragment;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CategoryDetailFragment extends GenericRetainedToolbarFragment implements CategoryDetailContract.View{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO
    }

    @Override
    public void displayAllCoursesOfCategory(List<Course> list) {

    }

    @Override
    public void goToCourseDetailScreen(int position) {

    }
}
