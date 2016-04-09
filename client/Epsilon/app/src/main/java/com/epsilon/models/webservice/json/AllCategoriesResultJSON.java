package com.epsilon.models.webservice.json;

import com.epsilon.models.entities.Category;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public class AllCategoriesResultJSON extends GenericResultJSON{

    List<Category> message;

    public List<Category> getMessage() {
        return message;
    }

    public void setMessage(List<Category> message) {
        this.message = message;
    }
}
