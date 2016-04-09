package com.epsilon.screens.category;

import com.epsilon.commons.GenericViewInterface;
import com.epsilon.models.entities.Category;

import java.util.List;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface CategoryContract {

    interface View extends GenericViewInterface{
        void displayAllCategory(List<Category> categories);
        void goToCategoryCoursesScreen(int categoryId);
    }

    interface UserActionListener {
        void getAllCategory();
        void viewCoursesOfCategory(int categoryId);

    }
}
