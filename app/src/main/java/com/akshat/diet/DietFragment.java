package com.akshat.diet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class DietFragment extends Fragment implements View.OnClickListener {

    private MaterialCardView breakfastCard, lunchCard, dinnerCard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lunchCard = view.findViewById(R.id.lunchCard);
        breakfastCard = view.findViewById(R.id.breakfastCard);
        dinnerCard = view.findViewById(R.id.dinnerCard);

        lunchCard.setOnClickListener(this);
        breakfastCard.setOnClickListener(this);
        dinnerCard.setOnClickListener(this);

        breakfastCard.callOnClick();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.breakfastCard:
                getDiet(breakfastCard, dinnerCard, lunchCard, "breakfast");
                break;
            case R.id.lunchCard:
                getDiet(lunchCard, breakfastCard, dinnerCard, "lunch");
                break;
            case R.id.dinnerCard:
                getDiet(dinnerCard, breakfastCard, lunchCard, "dinner");
                break;
        }
    }

    private void getDiet(ViewGroup v1, ViewGroup v2, ViewGroup v3, String type) {
        setActiveCard(v1);
        setInactiveCard(v2);
        setInactiveCard(v3);

        ArrayList<StructureDiet> allDiets = new ArrayList<>();
        allDiets = StaticData.addDiet(allDiets);

        LinearLayout dietContainer = getActivity().findViewById(R.id.dietContainer);
        dietContainer.removeAllViews();

        for (StructureDiet diet : allDiets) {
            if (diet.getCategory().equals(type)) {
                addLayout(diet);
            }
        }
    }

    private void setActiveCard(ViewGroup v) {
        ((MaterialCardView) v).setCardBackgroundColor(getResources().getColor(R.color.background));
        ((TextView) v.getChildAt(0)).setTextColor(getResources().getColor(R.color.textColorBg));
        ((TextView) v.getChildAt(0)).getCompoundDrawablesRelative()[2].setTint(getResources().getColor(R.color.textColorBg));
    }

    private void setInactiveCard(ViewGroup v) {
        ((MaterialCardView) v).setCardBackgroundColor(getResources().getColor(R.color.lightWhite));
        ((TextView) v.getChildAt(0)).setTextColor(getResources().getColor(R.color.background));
        ((TextView) v.getChildAt(0)).getCompoundDrawablesRelative()[2].setTint(getResources().getColor(R.color.background));
    }


    private void addLayout(StructureDiet diet) {

        LinearLayout dietContainer = getActivity().findViewById(R.id.dietContainer);
        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.diet_inflator_layout, dietContainer, false);

        TextView dietName = v.findViewById(R.id.dietName);
        TextView calorieCount = v.findViewById(R.id.calorieCount);
        LinearLayout contentHolder = v.findViewById(R.id.contentHolder);

        dietName.setText(diet.getDietName());
        calorieCount.setText(diet.getCalorie() + "Cal");

        String[] contents = diet.getDietContents().split(":");
        for (String content : contents) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.text_list_template, null);
            textView.setText("\u25cf  " + content);
            contentHolder.addView(textView);
        }

        dietContainer.addView(v);
    }
}