package com.epsilon.models.webservice;

import com.epsilon.models.webservice.json.AllCategoriesResultJSON;
import com.epsilon.models.webservice.json.CourseResultJSON;
import com.epsilon.models.webservice.json.CoursesListResultJSON;
import com.epsilon.models.webservice.json.EnrollRequestJSON;
import com.epsilon.models.webservice.json.GenericResultJSON;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("/courses/{id}/recommends")
    Call<CoursesListResultJSON> getRecommendedCourseWhenOpeningCourse(@Path("id") int courseId);

    @GET("/learners/history")
    Call<CoursesListResultJSON> getMyCourses();

    @GET("/learners/recommendations")
    Call<CoursesListResultJSON> getRecommendedCourseByHistory();

    @POST("/learners/enroll")
    Call<GenericResultJSON> enrollCourse(@Body EnrollRequestJSON enrollRequestJSON);

}
