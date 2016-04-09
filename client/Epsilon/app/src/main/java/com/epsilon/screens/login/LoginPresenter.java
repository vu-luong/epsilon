package com.epsilon.screens.login;

import android.text.TextUtils;

import com.epsilon.utils.Validator;

/**
 * Created by Dandoh on 4/9/16.
 */
public class LoginPresenter implements LoginContract.UserActionListener{

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
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

        mView.displayLoginSucceed();
        mView.goToMainScreen();
    }

    @Override
    public void toRegister() {
        mView.goToRegisterScreen();
    }
}
