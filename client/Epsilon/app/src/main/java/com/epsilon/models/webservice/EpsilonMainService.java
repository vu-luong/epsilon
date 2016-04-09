package com.epsilon.models.webservice;

import com.epsilon.models.webservice.json.AllCategoriesResultJSON;
import com.epsilon.models.webservice.json.CourseResultJSON;
import com.epsilon.models.webservice.json.CoursesListResultJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface EpsilonMainService {

    @GET("/categories")
    Call<AllCategoriesResultJSON> getAllCategories();

    @GET("/categories/{id}/all")
    Call<CoursesListResultJSON> getCoursesOfCategory(@Path("id") int id);

    @GET("/courses/{id}")
    Call<CourseResultJSON> getCourseById(@Path("id") int id);

    @GET("/course/{id}/recommends")
    Call<CoursesListResultJSON> getRecommendedCourseWhenOpeningCourse(@Path("id") int courseId);

}
