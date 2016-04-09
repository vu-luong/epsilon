package com.epsilon.screens.register;

import android.text.TextUtils;

import com.epsilon.models.user.UserRepository;
import com.epsilon.utils.Validator;

/**
 * Created by Dandoh on 4/9/16.
 */
public class RegisterPresenter implements RegisterContract.UserActionListener {

    private RegisterContract.View mRegisterView;
    private UserRepository mUserRepository;

    public RegisterPresenter(RegisterContract.View mRegisterView, UserRepository mUserRepository) {
        this.mRegisterView = mRegisterView;
        this.mUserRepository = mUserRepository;
    }

    @Override
    public void completeBasic(String username, String password) {
        if (TextUtils.isEmpty(username) || !Validator.isNameValid(username)) {
            mRegisterView.displayErrorUsername();
            return;
        }

        if (TextUtils.isEmpty(password) || !Validator.isPasswordValid(password)) {
            mRegisterView.displayErrorPassword();
            return;
        }


        mRegisterView.goToRegisterAddCategory();
    }

    @Override
    public void register(String username, String password, int[] favoriteLevel) {
        // TODO

        mUserRepository.register(username, password, favoriteLevel, new UserRepository.SignUpResultCallBack() {
            @Override
            public void onSucceed() {
                mRegisterView.displayRegisterSucceed();
                mRegisterView.goToMainScreen();
            }

            @Override
            public void onError(String errorMessage) {
                mRegisterView.displayRegisterError(errorMessage);
            }
        });
    }
}
