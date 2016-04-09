package com.epsilon.models.webservice.json;

import com.epsilon.models.entities.Course;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CourseResultJSON extends GenericResultJSON{

    Course message;

    public Course getMessage() {
        return message;
    }

    public void setMessage(Course message) {
        this.message = message;
    }
}
