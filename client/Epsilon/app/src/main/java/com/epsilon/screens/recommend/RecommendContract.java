package com.epsilon.screens.recommend;

import com.epsilon.commons.GenericViewInterface;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/10/16.
 */
public interface RecommendContract {

    interface View extends GenericViewInterface {
        void displayRecommendCourse(List<Course> courses);
        void goToCourseDetail(int courseId);
        void onError(String error);
    }

    interface UserActionListener {
        void getRecommendCourses();
        void viewCourseDetail(int position, Course course);
    }

}
