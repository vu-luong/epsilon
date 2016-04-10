package com.epsilon.screens.register;

import android.text.TextUtils;

import com.epsilon.models.user.UserRepository;
import com.epsilon.utils.Validator;

import org.w3c.dom.Text;

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
    public void completeBasic(String username, String password, String repassword) {
        if (TextUtils.isEmpty(username) || !Validator.isUsernameValid(username)) {
            mRegisterView.displayErrorUsername();
            return;
        }


        if (TextUtils.isEmpty(password) || !Validator.isPasswordValid(password)) {
            mRegisterView.displayErrorPassword();
            return;
        }

        if (!password.equals(repassword)) {
            mRegisterView.displayPasswordNotMatchError();
            return;
        }


        mRegisterView.showProcessingIndicator(true);
        mUserRepository.checkUsernameExist(username, new UserRepository.CheckUsernameExistCallBack() {
            @Override
            public void onOkUsernameNotExist() {
                mRegisterView.showProcessingIndicator(false);
                mRegisterView.goToRegisterAddCategory();
            }

            @Override
            public void onError(String errorMessage) {
                mRegisterView.showProcessingIndicator(false);
                mRegisterView.displayRegisterError(errorMessage);
            }
        });
    }

    @Override
    public void register(String username, String password, int[] favoriteLevel) {
        // TODO

        mRegisterView.showProcessingIndicator(true);
        mUserRepository.register(username, password, favoriteLevel, new UserRepository.SignUpResultCallBack() {
            @Override
            public void onSucceed() {
                mRegisterView.displayRegisterSucceed();
                mRegisterView.goToMainScreen();
                mRegisterView.showProcessingIndicator(false);
            }

            @Override
            public void onError(String errorMessage) {
                mRegisterView.displayRegisterError(errorMessage);
                mRegisterView.showProcessingIndicator(false);
            }
        });
    }
}
