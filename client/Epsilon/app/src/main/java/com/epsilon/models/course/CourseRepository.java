package com.epsilon.models.course;

import com.epsilon.commons.GenericCallback;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public interface CourseRepository {

    void getCoursesOfCategory(int id, CoursesListResultCallBack callBack);

    interface CoursesListResultCallBack extends GenericCallback {
        void onSucceed(List<Course> courses);
    }

    void getCourseById(int id, CourseResultCallBack callBack);

    interface CourseResultCallBack extends GenericCallback {
        void onSucceed(Course course);
    }

    void getRecommendedCourseWhenOpeningCourse(int courseId,
                                               CoursesListResultCallBack callBack);

    void getMyCourses(CoursesListResultCallBack callBack);


    void getRecommendedCourseByHistory(CoursesListResultCallBack callBack);

    void enrollCourse(int courseId, EnrollCourseCallBack callBack);

    interface EnrollCourseCallBack extends GenericCallback {
        void onSucceed();
    }
}
