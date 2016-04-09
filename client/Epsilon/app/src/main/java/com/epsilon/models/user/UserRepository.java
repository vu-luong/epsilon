package com.epsilon.models.user;


import com.epsilon.commons.GenericCallback;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface UserRepository {

    void register(String username, String password, int[] favoriteLevel, SignUpResultCallBack callBack);
    interface SignUpResultCallBack extends GenericCallback{
        void onSucceed();
    }

    void login(String username, String password, LoginResultCallBack callBack);
    interface LoginResultCallBack extends GenericCallback{
        void onSucceed();
    }



}
