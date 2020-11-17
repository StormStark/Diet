package com.akshat.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.util.HashMap;

public class ExerciseFragment extends Fragment implements View.OnClickListener {

    private ImageView goBackImage;
    private MaterialCardView fullBodyCard, legsCard, armsCard, chestCard;
    private Button getStarted;
    private HashMap<String, String> arguments = new HashMap<>();

    public ExerciseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goBackImage = view.findViewById(R.id.goBackImage);
        fullBodyCard = view.findViewById(R.id.fullBodyCard);
        legsCard = view.findViewById(R.id.legsCard);
        armsCard = view.findViewById(R.id.armsCard);
        chestCard = view.findViewById(R.id.chestCard);
        getStarted = view.findViewById(R.id.getStarted);

        goBackImage.setOnClickListener(this);
        fullBodyCard.setOnClickListener(this);
        legsCard.setOnClickListener(this);
        armsCard.setOnClickListener(this);
        chestCard.setOnClickListener(this);
        getStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goBackImage:
                getActivity().onBackPressed();
                break;

            case R.id.legsCard:
                arguments.clear();
                arguments.put("exerciseType", "Legs");
                UtilityFunctions.loadFragment(getActivity(), new CustomExercise(), arguments);
                break;

            case R.id.armsCard:
                arguments.clear();
                arguments.put("exerciseType", "Arms");
                UtilityFunctions.loadFragment(getActivity(), new CustomExercise(), arguments);
                break;

            case R.id.fullBodyCard:
                arguments.clear();
                arguments.put("exerciseType", "Core");
                UtilityFunctions.loadFragment(getActivity(), new CustomExercise(), arguments);
                break;

            case R.id.chestCard:
                arguments.clear();
                arguments.put("exerciseType", "Chest");
                UtilityFunctions.loadFragment(getActivity(), new CustomExercise(), arguments);
                break;
            case R.id.getStarted:
                UtilityFunctions.loadFragment(getActivity(), new ExerciseTarget(), null);
                break;
        }
    }
}