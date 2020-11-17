package com.akshat.diet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public static final String MyPREFERENCES = "LoginPreference";
    public String bmiHeight, bmiWeight;
    SharedPreferences loginPreferences;

    private TextView weightTextBmi, heightTextBmi, bmiValue;
    private MaterialCardView exerciseCard, dietCard, articleCard;

    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Users user = UtilityFunctions.getLoginPreference(loginPreferences);

        if (user == null) {
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
        }

        bmiHeight = user.getUserHeightInCM();
        bmiWeight = user.getUserWeightInKG();
        weightTextBmi = view.findViewById(R.id.weightTextBmi);
        heightTextBmi = view.findViewById(R.id.heightTextBmi);
        bmiValue = view.findViewById(R.id.bmiValue);
        exerciseCard = view.findViewById(R.id.personalizedExercise);
        dietCard = view.findViewById(R.id.personalizedDiet);
        articleCard = view.findViewById(R.id.recommendedArticles);

        weightTextBmi.setText(bmiWeight + " KG");
        heightTextBmi.setText(bmiHeight + " CM");
        calculateBMI();

        weightTextBmi.setOnClickListener(this);
        heightTextBmi.setOnClickListener(this);
        exerciseCard.setOnClickListener(this);
        dietCard.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weightTextBmi:
                showWeightDialog();
                break;

            case R.id.heightTextBmi:
                showHeightDialog();
                break;

            case R.id.personalizedExercise:
                UtilityFunctions.loadFragment(getActivity(), new ExerciseFragment(), null);
                break;

            case R.id.personalizedDiet:
                UtilityFunctions.loadFragment(getActivity(), new DietFragment(), null);
                break;
        }
    }

    private void showWeightDialog() {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(getActivity(), R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                .setTitle("Select Weight (KG)")
                .setPositiveButton("Select", null);

        NumberPicker kgNumberPicker = new NumberPicker(getActivity());
        kgNumberPicker.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5F));
        String kgValues[] = new String[200];
        for (int i = 0; i < kgValues.length; i++) {
            kgValues[i] = i + " kg";
        }
        kgNumberPicker.setMaxValue(kgValues.length - 1);
        if (bmiWeight.isEmpty())
            kgNumberPicker.setValue(60);
        else
            kgNumberPicker.setValue(Integer.parseInt(bmiWeight.split("\\.")[0]));
        kgNumberPicker.setDisplayedValues(kgValues);
        kgNumberPicker.setMinValue(0);
        kgNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                bmiWeight = i1 + "";
                weightTextBmi.setText(bmiWeight + " KG");
                calculateBMI();
                Log.d("MIKE", i1 + " : " + bmiWeight);
            }
        });

        NumberPicker gramNumberPicker = new NumberPicker(getActivity());
        gramNumberPicker.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5F));
        String gramValues[] = new String[10];
        for (int i = 0; i < gramValues.length; i++) {
            gramValues[i] = i * 100 + " g";
        }
        gramNumberPicker.setMaxValue(gramValues.length - 1);
        if (bmiWeight.isEmpty() || !bmiWeight.contains("."))
            gramNumberPicker.setValue(0);
        else
            gramNumberPicker.setValue(Integer.parseInt(bmiWeight.split("\\.")[1]));
        gramNumberPicker.setDisplayedValues(gramValues);
        gramNumberPicker.setMinValue(0);
        gramNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                if (i1 != 0) {
                    if (bmiWeight.contains("."))
                        bmiWeight = bmiWeight.split("\\.")[0] + "." + i1;
                    else
                        bmiWeight = bmiWeight + "." + i1;
                } else
                    bmiWeight = bmiWeight.split("\\.")[0];
                calculateBMI();
                Log.d("MIKE", i1 + " : " + bmiWeight);
                weightTextBmi.setText(bmiWeight + " KG");
            }
        });

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        );

        linearLayout.addView(kgNumberPicker);
        linearLayout.addView(gramNumberPicker);

        dialog.setView(linearLayout);
        dialog.show().getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getActivity().getResources().getColor(R.color.textColorBg));
    }

    private void showHeightDialog() {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(getActivity(), R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                .setTitle("Select Height (CM)")
                .setPositiveButton("Select", /* listener = */ null);

        NumberPicker aNumberPicker = new NumberPicker(getActivity());
        aNumberPicker.setMaxValue(250);
        if (bmiHeight.isEmpty())
            aNumberPicker.setValue(182);
        else
            aNumberPicker.setValue(Integer.parseInt(bmiHeight));
        aNumberPicker.setMinValue(90);
        aNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                heightTextBmi.setText(i1 + " CM");
                bmiHeight = i1 + "";
                calculateBMI();
            }
        });

        dialog.setView(aNumberPicker);
        dialog.show().getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getActivity().getResources().getColor(R.color.textColorBg));
    }

    private void calculateBMI() {
        float heightInMeters = Float.parseFloat(bmiHeight) / 100;
        float BMI = Float.parseFloat(bmiWeight) / (heightInMeters * heightInMeters);
        String result = String.format("%.2f", BMI);

        bmiValue.setText(result);
        GradientDrawable sd = (GradientDrawable) bmiValue.getBackground().mutate();
        if (BMI < 18.5)
            sd.setColor(Color.parseColor("#FEB132"));
        else if (BMI < 25)
            sd.setColor(Color.parseColor("#30A232"));
        else if (BMI < 30)
            sd.setColor(Color.parseColor("#E96024"));
        else
            sd.setColor(Color.parseColor("#C0101B"));
    }
}