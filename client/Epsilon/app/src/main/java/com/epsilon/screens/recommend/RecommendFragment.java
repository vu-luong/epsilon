package com.epsilon.screens.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epsilon.R;
import com.epsilon.commons.GenericRetainedFragment;
import com.epsilon.customview.GridSpacingItemDecoration;
import com.epsilon.models.entities.Course;
import com.epsilon.screens.categorydetail.CourseListAdapter;
import com.epsilon.screens.coursedetail.CourseDetailActivity;
import com.epsilon.utils.Utils;

import java.util.List;

import butterknife.Bind;
import utils.Injection;

/**
 * Created by AnhVu on 4/10/16.
 */
public class RecommendFragment extends GenericRetainedFragment
        implements RecommendContract.View, CourseListAdapter.OnCourseItemClick {

    private RecommendContract.UserActionListener mUserActionListener;
    private CourseListAdapter mCourseListAdapter;

    @Bind(R.id.recommend_recycle_view)
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.recommend_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCourseListAdapter = new CourseListAdapter(this);
        mUserActionListener = new RecommendPresenter(this, Injection.provideCourseRepository());
        mUserActionListener.getRecommendCourses();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpList();

        setUpSwipeRefreshLayout();
    }

    private void setUpSwipeRefreshLayout() {

        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Load request, force update
                mUserActionListener.getRecommendCourses();
            }
        });
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
    public void displayRecommendCourse(List<Course> courses) {
        mCourseListAdapter.setCourses(courses);
    }

    @Override
    public void goToCourseDetail(int courseId) {
        Intent intent = CourseDetailActivity.makeIntent(getActivity(), courseId);
        startActivity(intent);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(int position, Course course) {
        mUserActionListener.viewCourseDetail(position, course);
    }

    @Override
    protected void showLoading() {
        isProcessing = true;
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    protected void dismissLoading() {
        isProcessing = false;

        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
