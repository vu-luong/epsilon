package com.epsilon.screens.coursedetail;

import com.epsilon.models.course.CourseRepository;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CourseDetailPresenter implements CourseDetailContract.UserActionListener{
    private final CourseDetailContract.View mView;
    private final CourseRepository mCourseRepository;
    private Course mCurrentCourse;

    public CourseDetailPresenter(CourseDetailContract.View view, CourseRepository courseRepositoryApi) {
        this.mView = view;
        this.mCourseRepository = courseRepositoryApi;
    }

    @Override
    public void getCourseDetailById(int id) {
        mView.showProcessingIndicator(true);

        mCourseRepository.getCourseById(id, new CourseRepository.CourseResultCallBack() {
            @Override
            public void onSucceed(Course course) {
                mView.showProcessingIndicator(false);
                mCurrentCourse = course;
                mView.displayCourseDetail(course);
            }

            @Override
            public void onError(String errorMessage) {
                mView.showProcessingIndicator(false);
                mView.onError(errorMessage);
            }
        });

    }

    @Override
    public void goToCourse() {
        if (mCurrentCourse != null)
            mView.goToCourseUrl(mCurrentCourse.getLink());
    }

    @Override
    public void getRecommendedCourse(int courseId) {
        mCourseRepository.getRecommendedCourseWhenOpeningCourse(courseId,
                new CourseRepository.CoursesListResultCallBack() {
                    @Override
                    public void onSucceed(List<Course> courses) {
                        mView.displayRecommendedCourse(courses);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        mView.onError(errorMessage);
                    }
                });

    }
}
