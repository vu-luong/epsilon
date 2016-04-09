package com.epsilon.screens.register;

import com.epsilon.commons.GenericViewInterface;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface RegisterContract {

    interface View extends GenericViewInterface{
        void goToRegisterAddCategory();
        void displayErrorUsername();
        void displayErrorPassword();
        void goToMainScreen();
        void displayRegisterSucceed();
        void displayRegisterError(String errorMessage);
    }

    interface UserActionListener {
        void completeBasic(String username, String password);
        void register(String username, String password, int[] favoriteLevel);
    }
}
