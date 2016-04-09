package com.epsilon.models.user;


/**
 * Created by Dandoh on 4/9/16.
 */
public interface UserRepository {

    void register(String username, String password, SignUpResultCallBack callBack);
    interface SignUpResultCallBack {
        void onSucceed();
        void onError(String message);
    }

    void login(String username, String password, LoginResultCallBack callBack);
    interface LoginResultCallBack {
        void onSucceed();
        void onError(String message);
    }

}
