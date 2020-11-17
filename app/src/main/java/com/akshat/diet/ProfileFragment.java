package com.akshat.diet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public static final String MyPREFERENCES = "LoginPreference";
    SharedPreferences loginPreferences;

    private TextView heightText, weightText, ageText, genderText, userFullNameText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Users user = UtilityFunctions.getLoginPreference(loginPreferences);

        heightText = view.findViewById(R.id.heightText);
        weightText = view.findViewById(R.id.weightText);
        ageText = view.findViewById(R.id.ageText);
        genderText = view.findViewById(R.id.genderText);
        userFullNameText = view.findViewById(R.id.userFullNameText);

        heightText.setText("\u25cf  Height : " + user.getUserHeightInCM() + " CM");
        weightText.setText("\u25cf  Weight : " + user.getUserWeightInKG() + " KG");
        ageText.setText("\u25cf  Age : " + user.getAge() + " Y");
        genderText.setText("\u25cf  Gender : " + user.getGender());
        userFullNameText.setText(user.getFullName());
    }
}