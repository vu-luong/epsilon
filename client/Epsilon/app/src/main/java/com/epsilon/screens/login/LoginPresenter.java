package com.epsilon.screens.login;

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
        // TODO



        mView.displayLoginSucceed();
        mView.goToMainScreen();
    }

    @Override
    public void toRegister() {
        mView.goToRegisterScreen();
    }
}
