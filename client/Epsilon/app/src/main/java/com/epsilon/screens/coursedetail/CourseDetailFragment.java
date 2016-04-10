package com.epsilon.screens.coursedetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.epsilon.R;
import com.epsilon.commons.GenericRetainedToolbarFragment;
import com.epsilon.models.entities.Course;
import com.epsilon.screens.categorydetail.CourseListAdapter;
import com.epsilon.screens.main.MainActivity;
import com.epsilon.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import utils.Injection;


/**
 * Created by AnhVu on 4/9/16.
 */
public class CourseDetailFragment extends GenericRetainedToolbarFragment
        implements CourseDetailContract.View, CourseListAdapter.OnCourseItemClick {

    private static final String COURSE_ID_KEY = "course id";
    private CourseDetailContract.UserActionListener mUserActionListener;
    private HorizontalCourseListAdapter mCourseListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.coursedetail_btn_gotocourse)
    Button mGoToCourseButton;
    @Bind(R.id.coursedetail_iv_thumbnail)
    ImageView mThumbnailImageView;
    @Bind(R.id.coursedetail_ratingbar)
    RatingBar mRatingBar;
    @Bind(R.id.coursedetail_recycleview_recommend)
    RecyclerView mRecyclerView;
    @Bind(R.id.coursedetail_tv_author)
    TextView mAuthorTextView;
    @Bind(R.id.coursedetail_tv_description)
    TextView mDescriptionTextView;
    @Bind(R.id.coursedetail_tv_title)
    TextView mTitleTextView;

    public static Fragment getInstance(int courseId) {
        Bundle bundle = new Bundle();
        bundle.putInt(COURSE_ID_KEY, courseId);

        Fragment fragment = new CourseDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserActionListener = new CourseDetailPresenter(this, Injection.provideCourseRepository());

        mCourseListAdapter = new HorizontalCourseListAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpList();

        mUserActionListener.getCourseDetailById(getArguments().getInt(COURSE_ID_KEY));
    }

    private void setUpList() {
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCourseListAdapter);
    }

    @Override
    public void displayCourseDetail(Course course) {
        Picasso.with(getActivity())
                .load(course.getImage_url())
                .into(mThumbnailImageView);

        mTitleTextView.setText(course.getTitle());
        mAuthorTextView.setText(course.getAuthor_name());
        mRatingBar.setRating(course.getRating());
        mDescriptionTextView.setText(course.getDescription());

        mUserActionListener.getRecommendedCourse(course.getId());


        Utils.log(TAG, course.isLearned() + " ");

        if (course.isLearned()) mGoToCourseButton.setText(R.string.go_to_class);
        else mGoToCourseButton.setText(R.string.go_to_course);

    }

    @OnClick(R.id.coursedetail_btn_gotocourse)
    void onGoToCourseClick() {
        mUserActionListener.enrollCourse();
        mUserActionListener.goToCourse();
    }

    @Override
    public void goToCourseUrl(String url) {
        Intent mainIntent = MainActivity.makeIntent(getActivity(), MainActivity.COURSES_TAB_POSITION);
        startActivity(mainIntent);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);

    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(), error + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRecommendedCourse(List<Course> courses) {
        mCourseListAdapter.setCourses(courses);
    }

    @Override
    public void onClick(int position, Course course) {
        //TODO
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        return false;
    }
}
