package com.epsilon.screens.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.epsilon.screens.coursedetail.HorizontalCourseListAdapter;

import java.util.List;

import butterknife.Bind;
import utils.Injection;

/**
 * Created by AnhVu on 4/10/16.
 */
public class RecommendFragment extends GenericRetainedFragment
        implements RecommendContract.View, CourseListAdapter.OnCourseItemClick {

    private RecommendContract.UserActionListener mUserActionListener;

    @Bind(R.id.recommend_recycle_view_favorite)
    RecyclerView mFavoriteRecyclerView;
    private CourseListAdapter mFavoriteCourseListAdapter;
    private RecyclerView.LayoutManager mFavoriteLayoutManager;


    @Bind(R.id.recommend_recycle_view_user)
    RecyclerView mUserBasedRecyclerView;
    private CourseListAdapter mUserBasedCourseListAdapter;
    private RecyclerView.LayoutManager mUserBasedLayoutManager;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFavoriteCourseListAdapter = new HorizontalCourseListAdapter(this);
        mUserActionListener = new RecommendPresenter(this, Injection.provideCourseRepository());
        mUserActionListener.getFavoriteRecommendCourses();

        mUserBasedCourseListAdapter = new HorizontalCourseListAdapter(this);
        mUserActionListener.getUserBasedRecommendCourses();
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

//        setUpSwipeRefreshLayout();
    }

//    private void setUpSwipeRefreshLayout() {
//
//        mSwipeRefreshLayout.setColorSchemeColors(
//                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
//                ContextCompat.getColor(getActivity(), R.color.colorAccent),
//                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Load request, force update
//                mUserActionListener.getFavoriteRecommendCourses();
//            }
//        });
//    }

    private void setUpList() {

        int numOfColumn = getResources().getInteger(R.integer.numOfColumn);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
//        mFavoriteLayoutManager = new GridLayoutManager(getActivity(), numOfColumn);
        mFavoriteLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mFavoriteRecyclerView.setLayoutManager(mFavoriteLayoutManager);
//        mFavoriteRecyclerView.addItemDecoration(new GridSpacingItemDecoration(numOfColumn, spacingInPixels, true));
        mFavoriteRecyclerView.setAdapter(mFavoriteCourseListAdapter);

//        mUserBasedLayoutManager = new GridLayoutManager(getActivity(), numOfColumn);
        mUserBasedLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mUserBasedRecyclerView.setLayoutManager(mUserBasedLayoutManager);
//        mUserBasedRecyclerView.addItemDecoration(new GridSpacingItemDecoration(numOfColumn, spacingInPixels, true));
        mUserBasedRecyclerView.setAdapter(mUserBasedCourseListAdapter);

    }

    @Override
    public void displayFavoriteRecommendCourse(List<Course> courses) {
        mFavoriteCourseListAdapter.setCourses(courses);
    }

    @Override
    public void displayUserBasedRecommendCourse(List<Course> courses) {
        mUserBasedCourseListAdapter.setCourses(courses);
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

}
