package com.akshat.diet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;

public class UtilityFunctions {

    public static void showSnackbar(View view, String message, int duration) {
        Snackbar snackbar;
        snackbar = Snackbar.make(view, message, duration);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(view.getResources().getColor(R.color.background));
        TextView tv = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(view.getResources().getColor(R.color.textColorBg));
        snackbar.show();
    }

    public static boolean inputMinLengthValidation(TextInputLayout textInputLayout, int length) {
        String value = textInputLayout.getEditText().getText().toString().trim();
        String errorMessage = null;
        textInputLayout.setError(null);
        if (value.isEmpty())
            errorMessage = "Required";
        else if (value.length() < length)
            errorMessage = "Min " + length + " characters";

        textInputLayout.setError(errorMessage);
        return errorMessage == null;
    }

    public static void addLoginPreference(SharedPreferences sharedPreferences, Object myObject) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myObject);
        prefsEditor.putString("CurrentUser", json);
        prefsEditor.commit();
    }

    public static Users getLoginPreference(SharedPreferences sharedPreferences) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("CurrentUser", "");
        Users user = gson.fromJson(json, Users.class);
        return user;
    }

    public static void removeLoginPreference(SharedPreferences sharedPreferences) {
        sharedPreferences.edit().remove("CurrentUser").apply();
    }

    public static void loadFragment(FragmentActivity activity, Fragment fragment, HashMap<String, String> arguments) {

        if (arguments != null) {
            Bundle bundle = new Bundle();
            Iterator myVeryOwnIterator = arguments.keySet().iterator();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = (String) arguments.get(key);
                bundle.putString(key, value);
            }
            fragment.setArguments(bundle);
        }
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit();
    }
}