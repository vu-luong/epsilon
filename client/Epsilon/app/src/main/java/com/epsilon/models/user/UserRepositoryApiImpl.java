package com.epsilon.models.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.epsilon.EpsilonApplication;
import com.epsilon.commons.GenericRetrofitCallback;
import com.epsilon.models.webservice.ServiceGenerator;
import com.epsilon.models.webservice.json.LoginRequestJSON;
import com.epsilon.models.webservice.json.LoginResultJSON;
import com.epsilon.utils.Constants;
import com.epsilon.utils.Utils;


public class UserRepositoryApiImpl implements UserRepository {

    private static final String TAG = "UserRepositoryApiImpl";
    private static UserRepository instance;

    private Context mContext = EpsilonApplication.getAppContext();

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryApiImpl();
        }

        return instance;
    }


    @Override
    public void register(String username, String password, final SignUpResultCallBack callBack) {

    }

    @Override
    public void login(String username, String password, final LoginResultCallBack callBack) {
        ServiceGenerator.getEpsilonUserService()
                .login(new LoginRequestJSON(username, password))
                .enqueue(new GenericRetrofitCallback<LoginResultJSON>() {
                    @Override
                    protected void onSucceed(LoginResultJSON result) {
                        Utils.log(TAG, result.getMessage().getInsertId() + " ");
                        callBack.onSucceed();
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }
}
