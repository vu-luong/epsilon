package com.epsilon.models.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.epsilon.EpsilonApplication;
import com.epsilon.models.webservice.ServiceGenerator;
import com.epsilon.models.webservice.json.LoginRequestJSON;
import com.epsilon.utils.Constants;


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
    public void register(String username, String password, SignUpResultCallBack callBack) {
        ServiceGenerator.getEpsilonUserService()
                .login(new LoginRequestJSON(username, password))
                .enqueue();
    }

    @Override
    public void login(String username, String password, LoginResultCallBack callBack) {

    }
}
