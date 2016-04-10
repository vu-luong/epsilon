package com.epsilon.screens.category;

import com.epsilon.models.category.CategoryRepository;
import com.epsilon.models.entities.Category;

import java.util.List;

/**
 * Created by Dandoh on 4/9/16.
 */
public class CategoryPresenter implements CategoryContract.UserActionListener{

    private CategoryContract.View mCategoryView;
    private CategoryRepository mCategoryRepository;

    public CategoryPresenter(CategoryContract.View mCategoryView, CategoryRepository mCategoryRepository) {
        this.mCategoryView = mCategoryView;
        this.mCategoryRepository = mCategoryRepository;
    }

    @Override
    public void getAllCategory() {
        mCategoryRepository.getAllCategories(new CategoryRepository.AllCategoriesResultCallBack() {
            @Override
            public void onSucceed(List<Category> categories) {
                mCategoryView.displayAllCategory(categories);
            }

            @Override
            public void onError(String errorMessage) {
                mCategoryView.onError(errorMessage);
            }
        });
    }

    @Override
    public void viewCoursesOfCategory(int categoryId, Category category) {
        mCategoryView.goToCategoryCoursesScreen(categoryId, category);
    }
}
