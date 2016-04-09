package com.epsilon.models.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.epsilon.EpsilonApplication;
import com.epsilon.commons.GenericRetrofitCallback;
import com.epsilon.models.webservice.ServiceGenerator;
import com.epsilon.models.webservice.json.CheckUsernameExistRequestJSON;
import com.epsilon.models.webservice.json.CheckUsernameExistResultJSON;
import com.epsilon.models.webservice.json.LoginRequestJSON;
import com.epsilon.models.webservice.json.LoginResultJSON;
import com.epsilon.models.webservice.json.RegisterRequestJSON;
import com.epsilon.models.webservice.json.RegisterResultJSON;
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
    public void register(String username, String password, int[] favorite,
                         final SignUpResultCallBack callBack) {
        String requirement_category = "";
        for (int i = 0; i < favorite.length; i++) {
            requirement_category += favorite[i];
        }

        ServiceGenerator.getEpsilonUserService()
                .register(new RegisterRequestJSON(username, password, requirement_category))
                .enqueue(new GenericRetrofitCallback<RegisterResultJSON>() {
                    @Override
                    protected void onSucceed(RegisterResultJSON result) {
                        Utils.log(TAG, "id = " + result.getMessage().getId());

                        SharedPreferences sharedPreferences = Utils.getSharedPreferences();
                        sharedPreferences.edit().putInt(Constants.ID_TAG, result.getMessage().getId()).commit();

                        callBack.onSucceed();

                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }

    @Override
    public void login(String username, String password, final LoginResultCallBack callBack) {
        ServiceGenerator.getEpsilonUserService()
                .login(new LoginRequestJSON(username, password))
                .enqueue(new GenericRetrofitCallback<LoginResultJSON>() {
                    @Override
                    protected void onSucceed(LoginResultJSON result) {

                        Utils.log(TAG, "on login succeed");
                        SharedPreferences sharedPreferences = Utils.getSharedPreferences();
                        sharedPreferences.edit().putInt(Constants.ID_TAG, result.getMessage().getId()).commit();

                        ServiceGenerator.resetService();
                        callBack.onSucceed();
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }

    @Override
    public void checkUsernameExist(String username, final CheckUsernameExistCallBack callBack) {
        ServiceGenerator.getEpsilonUserService()
                .checkUserNameExist(new CheckUsernameExistRequestJSON(username))
                .enqueue(new GenericRetrofitCallback<CheckUsernameExistResultJSON>() {
                    @Override
                    protected void onSucceed(CheckUsernameExistResultJSON result) {
                        callBack.onOkUsernameNotExist();
                    }

                    @Override
                    protected void onError(String message) {
                        callBack.onError(message);
                    }
                });
    }
}
