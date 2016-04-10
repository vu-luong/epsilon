package com.epsilon.screens.recommend;

import com.epsilon.models.course.CourseRepository;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/10/16.
 */
public class RecommendPresenter implements RecommendContract.UserActionListener {

    private final RecommendContract.View mView;
    private final CourseRepository mCourseRepository;

    public RecommendPresenter(RecommendContract.View mView, CourseRepository mCourseRepository) {
        this.mView = mView;
        this.mCourseRepository = mCourseRepository;
    }


    @Override
    public void getFavoriteRecommendCourses() {
        mView.showProcessingIndicator(true);

        mCourseRepository.getFavoriteRecommendedCourseByHistory(new CourseRepository.CoursesListResultCallBack() {
            @Override
            public void onSucceed(List<Course> courses) {
                mView.showProcessingIndicator(false);
                mView.displayFavoriteRecommendCourse(courses);
            }

            @Override
            public void onError(String errorMessage) {
                mView.showProcessingIndicator(false);
                mView.onError(errorMessage);
            }
        });

    }

    @Override
    public void getUserBasedRecommendCourses() {
        mView.showProcessingIndicator(true);

        mCourseRepository.getUserBasedRecommendedCourseByHistory(new CourseRepository.CoursesListResultCallBack() {
            @Override
            public void onSucceed(List<Course> courses) {
                mView.showProcessingIndicator(false);
                mView.displayUserBasedRecommendCourse(courses);
            }

            @Override
            public void onError(String errorMessage) {
                mView.showProcessingIndicator(false);
                mView.onError(errorMessage);
            }
        });
    }

    @Override
    public void viewCourseDetail(int position, Course course) {
        mView.goToCourseDetail(course.getId());
    }
}
