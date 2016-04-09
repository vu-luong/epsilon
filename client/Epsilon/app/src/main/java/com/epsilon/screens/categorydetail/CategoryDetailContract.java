package com.epsilon.screens.categorydetail;

import com.epsilon.commons.GenericViewInterface;
import com.epsilon.models.entities.Category;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public interface CategoryDetailContract {

    interface View extends GenericViewInterface {
        void displayAllCoursesOfCategory(List<Course> list);
        void goToCourseDetailScreen(int position);
        void displayNetworkError(String error);

    }

    interface UserActionListener {
        void getCoursesOfCategory(int id);
        void viewCourseDetail(int position);
    }

}
