package com.epsilon.screens.login;

import com.epsilon.commons.GenericViewInterface;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface LoginContract {


    interface View extends GenericViewInterface {
        void displayErrorUsername();
        void displayErrorPassword();
        void goToRegisterScreen();
        void displayLoginSucceed();
        void displayLoginError(String message);
        void goToMainScreen();
    }

    interface UserActionListener {
        void login(String username, String password);
        void toRegister();
    }
}
