package com.epsilon.screens.login;

import android.text.TextUtils;

import com.epsilon.models.user.UserRepository;
import com.epsilon.utils.Validator;

/**
 * Created by Dandoh on 4/9/16.
 */
public class LoginPresenter implements LoginContract.UserActionListener{

    private LoginContract.View mView;
    private UserRepository mUserRepository;

    public LoginPresenter(LoginContract.View mView, UserRepository mUserRepository) {
        this.mView = mView;
        this.mUserRepository = mUserRepository;
    }

    @Override
    public void login(String username, String password) {

        if (TextUtils.isEmpty(username) || !Validator.isNameValid(username)) {
            mView.displayErrorUsername();
            return;
        }

        if (TextUtils.isEmpty(password) || !Validator.isPasswordValid(password)) {
            mView.displayErrorPassword();
            return;
        }


        // TODO - send request login
        mUserRepository.login(username, password, new UserRepository.LoginResultCallBack() {
            @Override
            public void onSucceed() {
                mView.displayLoginSucceed();
                mView.goToMainScreen();
            }

            @Override
            public void onError(String message) {
                mView.displayLoginError(message);
            }
        });


    }

    @Override
    public void toRegister() {
        mView.goToRegisterScreen();
    }
}
