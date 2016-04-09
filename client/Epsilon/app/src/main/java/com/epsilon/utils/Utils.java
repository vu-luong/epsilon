package com.epsilon.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.epsilon.EpsilonApplication;

/**
 * Created by Dandoh on 3/8/16.
 */
public class Utils {

    public static String isNull(Object object) {
        return object == null ? "Null" : "Not null";
    }

    public static void log(String TAG, String message){
        Log.i(TAG, message);
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(EpsilonApplication.getAppContext());
    }

}
