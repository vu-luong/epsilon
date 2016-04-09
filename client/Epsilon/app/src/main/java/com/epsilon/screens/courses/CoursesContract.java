package com.epsilon.screens.courses;

import com.epsilon.commons.GenericViewInterface;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/10/16.
 */
public interface CoursesContract {

    interface View extends GenericViewInterface {
        void displayMyCourse(List<Course> courses);
        void goToCourseDetail(int courseId);
        void onError(String error);
    }

    interface UserActionListener {
        void getMyCourses();
        void viewCourseDetail(int position, Course course);
    }
}
