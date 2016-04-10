package com.epsilon.models.course;

import com.epsilon.commons.GenericRetrofitCallback;
import com.epsilon.models.entities.Course;
import com.epsilon.models.webservice.ServiceGenerator;
import com.epsilon.models.webservice.json.CourseResultJSON;
import com.epsilon.models.webservice.json.CoursesListResultJSON;
import com.epsilon.models.webservice.json.EnrollRequestJSON;
import com.epsilon.models.webservice.json.GenericResultJSON;

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
    public void getCoursesOfCategory(int id, final CoursesListResultCallBack callBack) {
        ServiceGenerator.getEpsilonWebService().getCoursesOfCategory(id)
                .enqueue(new GenericRetrofitCallback<CoursesListResultJSON>() {
                    @Override
                    protected void onSucceed(CoursesListResultJSON result) {
                        callBack.onSucceed(result.getMessage());
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }

    @Override
    public void getCourseById(int id, final CourseResultCallBack callBack) {

        if (mCachedCourses.containsKey(id)) {
            callBack.onSucceed(mCachedCourses.get(id));
        } else {
            ServiceGenerator.getEpsilonWebService()
                    .getCourseById(id).enqueue(new GenericRetrofitCallback<CourseResultJSON>() {
                @Override
                protected void onSucceed(CourseResultJSON result) {
                    mCachedCourses.put(result.getMessage().getId(), result.getMessage());
                    callBack.onSucceed(result.getMessage());
                }

                @Override
                protected void onError(String message) {
                    callBack.onError(message);
                }
            });
        }

    }

    @Override
    public void getRecommendedCourseWhenOpeningCourse(int courseId, final CoursesListResultCallBack callBack) {
        ServiceGenerator.getEpsilonWebService()
                .getRecommendedCourseWhenOpeningCourse(courseId)
                .enqueue(new GenericRetrofitCallback<CoursesListResultJSON>() {
                    @Override
                    protected void onSucceed(CoursesListResultJSON result) {
                        callBack.onSucceed(result.getMessage());
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }

    @Override
    public void getMyCourses(final CoursesListResultCallBack callBack) {
        ServiceGenerator.getEpsilonWebService()
                .getMyCourses()
                .enqueue(new GenericRetrofitCallback<CoursesListResultJSON>() {
                    @Override
                    protected void onSucceed(CoursesListResultJSON result) {
                        callBack.onSucceed(result.getMessage());
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }

    @Override
    public void getRecommendedCourseByHistory(final CoursesListResultCallBack callBack) {
        ServiceGenerator.getEpsilonWebService().getRecommendedCourseByHistory()
                .enqueue(new GenericRetrofitCallback<CoursesListResultJSON>() {
                    @Override
                    protected void onSucceed(CoursesListResultJSON result) {
                        callBack.onSucceed(result.getMessage());
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }

    @Override
    public void enrollCourse(int courseId, final EnrollCourseCallBack callBack) {
        // information of course had changed, delete from cached
        mCachedCourses.remove(courseId);

        ServiceGenerator.getEpsilonWebService()
                .enrollCourse(new EnrollRequestJSON(courseId))
                .enqueue(new GenericRetrofitCallback<GenericResultJSON>() {
                    @Override
                    protected void onSucceed(GenericResultJSON result) {
                        callBack.onSucceed();
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }

}
