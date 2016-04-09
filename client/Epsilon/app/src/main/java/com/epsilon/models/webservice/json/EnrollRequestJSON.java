package com.epsilon.models.webservice.json;

/**
 * Created by AnhVu on 4/10/16.
 */
public class EnrollRequestJSON {

    int course_id;

    public EnrollRequestJSON(int courseId) {
        this.course_id = courseId;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
