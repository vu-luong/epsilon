package com.epsilon.screens.coursedetail;

import com.epsilon.commons.GenericViewInterface;
import com.epsilon.models.entities.Course;

/**
 * Created by AnhVu on 4/9/16.
 */
public interface CourseDetailContract {

    interface View extends GenericViewInterface {
        void displayCourseDetail(Course course);
        void goToCourseUrl(String url);
        void onError(String error);

    }

    interface UserActionListener {
        void getCourseDetailById(int id);
        void enrollCourse();
    }
}
