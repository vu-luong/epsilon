package com.epsilon.screens.categorydetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epsilon.EpsilonApplication;
import com.epsilon.R;
import com.epsilon.commons.GenericRetainedToolbarFragment;
import com.epsilon.customview.GridSpacingItemDecoration;
import com.epsilon.models.entities.Course;
import com.epsilon.screens.category.CategoryListAdapter;

import java.util.List;

import butterknife.Bind;
import utils.Injection;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CategoryDetailFragment extends GenericRetainedToolbarFragment implements CategoryDetailContract.View{

    private static final String CATEGORY_KEY = "category key";
    private CategoryDetailContract.UserActionListener mUserActionListener;
    private CourseListAdapter mCourseListAdapter;


    @Bind(R.id.categorydetail_recycle_view)
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public static Fragment getInstance(int categoryId) {
        Bundle bundle = new Bundle();
        bundle.putInt(CATEGORY_KEY, categoryId);
        Fragment fragment = new CategoryDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserActionListener = new CategoryDetailPresenter(this, Injection.provideCourseRepository());
        mCourseListAdapter = new CourseListAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserActionListener.getCoursesOfCategory(getArguments().getInt(CATEGORY_KEY));

        setUpList();
    }

    private void setUpList() {
        int numOfColumn = getResources().getInteger(R.integer.numOfColumn);

        mLayoutManager = new GridLayoutManager(getActivity(), numOfColumn);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCourseListAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(numOfColumn, spacingInPixels, true));
    }

    @Override
    public void displayAllCoursesOfCategory(List<Course> list) {
        mCourseListAdapter.setCourses(list);
    }

    @Override
    public void goToCourseDetailScreen(int position) {
        // TODO
    }

    @Override
    public void displayNetworkError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
