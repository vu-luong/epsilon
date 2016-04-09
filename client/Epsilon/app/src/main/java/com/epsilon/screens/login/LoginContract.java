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
    }

    interface UserActionListener {
        void login(String username, String password);
        void toRegister();
    }
}
