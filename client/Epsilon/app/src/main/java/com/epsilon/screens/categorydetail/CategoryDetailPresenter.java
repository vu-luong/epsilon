package com.epsilon.screens.categorydetail;

import com.epsilon.models.course.CourseRepository;
import com.epsilon.models.course.CourseRepositoryApiImpl;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CategoryDetailPresenter implements CategoryDetailContract.UserActionListener{
    private CategoryDetailContract.View mCategoryDetailView;
    private CourseRepositoryApiImpl mCourseRepositoryApi;

    public CategoryDetailPresenter(CategoryDetailContract.View view, CourseRepositoryApiImpl courseRepositoryApi) {
        this.mCategoryDetailView = view;
        this.mCourseRepositoryApi = courseRepositoryApi;
    }

    @Override
    public void getCoursesOfCategory(int id) {
        mCategoryDetailView.showProcessingIndicator(true);
        mCourseRepositoryApi.getCoursesOfCategory(id, new CourseRepository.CoursesListResultCallBack() {
            @Override
            public void onSucceed(List<Course> courses) {
                mCategoryDetailView.showProcessingIndicator(false);
                mCategoryDetailView.displayAllCoursesOfCategory(courses);
            }

            @Override
            public void onError(String errorMessage) {
                mCategoryDetailView.showProcessingIndicator(false);
                mCategoryDetailView.displayNetworkError(errorMessage);
            }
        });
    }

    @Override
    public void viewCourseDetail(int position, Course course) {
        mCategoryDetailView.goToCourseDetailScreen(position, course.getId());
    }
}
