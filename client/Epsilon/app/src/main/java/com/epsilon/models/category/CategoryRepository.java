package com.epsilon.models.category;

import com.epsilon.commons.GenericCallback;
import com.epsilon.models.entities.Category;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public interface CategoryRepository {

    void getAllCategories(AllCategoriesResultCallBack callBack);
    interface AllCategoriesResultCallBack extends GenericCallback {
        void onSucceed(List<Category> categories);
    }
}
