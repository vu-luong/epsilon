package com.epsilon.models.webservice.json;

import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CoursesListResultJSON extends GenericResultJSON{

    List<Course> message;

    public List<Course> getMessage() {
        return message;
    }

    public void setMessage(List<Course> message) {
        this.message = message;
    }
}
