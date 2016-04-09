package com.epsilon;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dandoh on 4/9/16.
 */
public class EpsilonApplication extends Application{

    private static final String TAG = "EpsilonApplication";
    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;

    }

    public static Context getAppContext() {
        return mApplicationContext;
    }

}
