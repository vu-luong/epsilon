package com.epsilon.screens.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.epsilon.R;
import com.epsilon.commons.GenericRetainedFragment;
import com.epsilon.commons.GenericRetainedToolbarFragment;
import com.epsilon.utils.Utils;

import net.qiujuer.genius.widget.GeniusSeekBar;

import butterknife.Bind;
import butterknife.OnClick;
import utils.Injection;

/**
 * Created by Dandoh on 4/9/16.
 */
public class RegisterFragment extends GenericRetainedToolbarFragment implements RegisterContract.View {


    @Bind(R.id.register_edt_password)
    EditText mPasswordEditText;
    @Bind(R.id.register_edt_username)
    EditText mUsernameEditText;

    @Bind(R.id.seekBar1)
    GeniusSeekBar mSeekBar1;
    @Bind(R.id.seekBar2)
    GeniusSeekBar mSeekBar2;
    @Bind(R.id.seekBar3)
    GeniusSeekBar mSeekBar3;
    @Bind(R.id.seekBar4)
    GeniusSeekBar mSeekBar4;
    @Bind(R.id.seekBar5)
    GeniusSeekBar mSeekBar5;
    @Bind(R.id.seekBar6)
    GeniusSeekBar mSeekBar6;
    @Bind(R.id.seekBar7)
    GeniusSeekBar mSeekBar7;
    @Bind(R.id.seekBar8)
    GeniusSeekBar mSeekBar8;


    private int mCurrentScreenIndex = 1; // begin screen

    @Bind(R.id.wizard_container)
    ViewAnimator mWizardContainer;

    private RegisterContract.UserActionListener mUserActionListener;

    public static Fragment getInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserActionListener = new RegisterPresenter(this, Injection.provideUserRepository());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getView() != null) {
            getView().requestFocus();
            getView().setFocusableInTouchMode(true);
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (mCurrentScreenIndex == 2) {
                            wizardBack();
                            return true;
                        }
                    }

                    return false;
                }
            });
        }

        setUpSignUpWizardView();
    }

    void setUpSignUpWizardView() {
        // Kinda tricky, set display child if configuration change happen
        mWizardContainer.setDisplayedChild(mCurrentScreenIndex - 1);
    }

    private void wizardBack() {
        mCurrentScreenIndex--;
        mWizardContainer.setInAnimation(getContext(), R.anim.pull_in_left);
        mWizardContainer.setOutAnimation(getContext(), R.anim.push_out_right);
        mWizardContainer.showPrevious();
    }

    private void wizardNext() {
        mCurrentScreenIndex++;
        mWizardContainer.setInAnimation(getContext(), R.anim.pull_in_right);
        mWizardContainer.setOutAnimation(getContext(), R.anim.push_out_left);
        mWizardContainer.showNext();
    }

    @OnClick(R.id.register_btn_next)
    void completeBasic() {
        mUserActionListener.completeBasic(mUsernameEditText.getText().toString(),
                mPasswordEditText.getText().toString());
    }

    @OnClick(R.id.register_btn_complete)
    void completeRegister() {
        Utils.log(TAG, "Calling complete register");

        int[] favorite = new int[]{
                mSeekBar1.getProgress(),
                mSeekBar2.getProgress(),
                mSeekBar3.getProgress(),
                mSeekBar4.getProgress(),
                mSeekBar5.getProgress(),
                mSeekBar6.getProgress(),
                mSeekBar7.getProgress(),
                mSeekBar8.getProgress(),
        };

        mUserActionListener.register(mUsernameEditText.getText().toString(),
                mPasswordEditText.getText().toString(),
                favorite);

    }

    @Override
    public void goToRegisterAddCategory() {
        if (mCurrentScreenIndex == 1) {
            wizardNext();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (mCurrentScreenIndex == 2) {
                wizardBack();
            } else {
                getActivity().onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void displayErrorUsername() {
        Toast.makeText(getActivity(), "Username is invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayErrorPassword() {
        Toast.makeText(getActivity(), "Password is invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMainScreen() {
        Utils.log(TAG, "register succeed");
    }

    @Override
    public void displayRegisterSucceed() {
        Toast.makeText(getActivity(), "Register succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRegisterError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
