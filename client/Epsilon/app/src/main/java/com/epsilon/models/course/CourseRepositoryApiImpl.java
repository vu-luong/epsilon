package com.epsilon.models.course;

import com.epsilon.commons.GenericRetrofitCallback;
import com.epsilon.models.entities.Course;
import com.epsilon.models.webservice.ServiceGenerator;
import com.epsilon.models.webservice.json.CoursesOfCategoryResultJSON;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CourseRepositoryApiImpl implements CourseRepository {

    private static CourseRepositoryApiImpl instance;
    private HashMap<Integer, Course> mCachedCourses = new HashMap<>();

    public static CourseRepositoryApiImpl getInstance() {
        if (instance == null) instance = new CourseRepositoryApiImpl();

        return instance;
    }

    @Override
    public void getCoursesOfCategory(int id, final CoursesOfCategoryResultCallBack callBack) {
        ServiceGenerator.getEpsilonWebService().getCoursesOfCategory(id)
                .enqueue(new GenericRetrofitCallback<CoursesOfCategoryResultJSON>() {
                    @Override
                    protected void onSucceed(CoursesOfCategoryResultJSON result) {

                        List<Course> courses = result.getMessage();
                        for (Course course: courses) {
                            mCachedCourses.put(course.getId(), course);
                        }

                        callBack.onSucceed(result.getMessage());
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }
}
