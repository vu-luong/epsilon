package com.epsilon.screens.courses;

import com.epsilon.models.course.CourseRepository;
import com.epsilon.models.entities.Course;
import com.epsilon.screens.coursedetail.CourseDetailContract;

import java.util.List;

/**
 * Created by AnhVu on 4/10/16.
 */
public class CoursesPresenter implements CoursesContract.UserActionListener {

    private CoursesContract.View mCoursesView;
    private CourseRepository mCourseRepository;

    public CoursesPresenter(CoursesContract.View mCoursesView, CourseRepository mCourseRepository) {
        this.mCoursesView = mCoursesView;
        this.mCourseRepository = mCourseRepository;
    }


    @Override
    public void getMyCourses() {
        mCoursesView.showProcessingIndicator(true);

        mCourseRepository.getMyCourses(new CourseRepository.CoursesListResultCallBack() {
            @Override
            public void onSucceed(List<Course> courses) {
                mCoursesView.showProcessingIndicator(false);
                mCoursesView.displayMyCourse(courses);
            }

            @Override
            public void onError(String errorMessage) {
                mCoursesView.showProcessingIndicator(false);
                mCoursesView.onError(errorMessage);
            }
        });

    }

    @Override
    public void viewCourseDetail(int position, Course course) {
        mCoursesView.goToCourseDetail(course.getId());
    }
}
