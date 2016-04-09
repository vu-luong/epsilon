package com.epsilon.models.course;

import com.epsilon.commons.GenericCallback;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public interface CourseRepository {

    void getCoursesOfCategory(int id, CoursesOfCategoryResultCallBack callBack);

    interface CoursesOfCategoryResultCallBack extends GenericCallback {
        void onSucceed(List<Course> courses);
    }


}
