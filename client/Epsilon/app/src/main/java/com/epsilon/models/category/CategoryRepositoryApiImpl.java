package com.epsilon.models.category;

import com.epsilon.commons.GenericRetrofitCallback;
import com.epsilon.models.webservice.ServiceGenerator;
import com.epsilon.models.webservice.json.AllCategoriesResultJSON;

/**
 * Created by AnhVu on 4/9/16.
 */
public class CategoryRepositoryApiImpl implements CategoryRepository {
    private static CategoryRepository instance;

    public static CategoryRepository getInstance() {
        if (instance == null) instance = new CategoryRepositoryApiImpl();
        return instance;
    }

    @Override
    public void getAllCategories(final AllCategoriesResultCallBack callBack) {
        ServiceGenerator.getEpsilonWebService().getAllCategories().enqueue(new GenericRetrofitCallback<AllCategoriesResultJSON>() {
            @Override
            protected void onSucceed(AllCategoriesResultJSON result) {
                callBack.onSucceed(result.getMessage());
            }

            @Override
            protected void onError(String message) {
                callBack.onError(message);
            }
        });
    }

}
