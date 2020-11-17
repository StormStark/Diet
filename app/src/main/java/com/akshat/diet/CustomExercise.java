package com.akshat.diet;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CustomExercise extends Fragment {


    private TextView headingText;

    public CustomExercise() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        headingText = view.findViewById(R.id.headingText);

        String exerciseType = "";
        Bundle bundle = getArguments();
        if (bundle != null)
            exerciseType = bundle.getString("exerciseType");
        headingText.setText(exerciseType);

        ArrayList<StructureExercise> allExercises = new ArrayList<>();
        ArrayList<StructureExercise> exercises = new ArrayList<>();
        allExercises = StaticData.addExercise(allExercises);
        Log.d("Mike", allExercises.size() + "");

        for (StructureExercise e : allExercises) {
            if (e.getCategory().toUpperCase().equals(exerciseType.toUpperCase()))
                exercises.add(e);
        }

        for (int i = 0; i < exercises.size(); i++)
            addLayout(exercises.get(i));

    }

    private void addLayout(StructureExercise exercise) {

        LinearLayout exerciseContainer = getActivity().findViewById(R.id.exerciseContainer);

        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.exercise_inflator_layout, exerciseContainer, false);

        final ImageView exerciseImage = v.findViewById(R.id.exerciseImage);
        exerciseImage.setImageResource(getResources().getIdentifier(exercise.getImage(), "drawable", "com.akshat.diet"));

        final ImageView arrowImage = v.findViewById(R.id.arrowImage);

        final TextView exerciseName, exerciseDescription;
        exerciseName =  v.findViewById(R.id.exerciseName);
        exerciseDescription =  v.findViewById(R.id.exerciseDescription);

        exerciseName.setText(exercise.getName());
        exerciseDescription.setText(exercise.getDescription());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (exerciseImage.getVisibility() == View.GONE){
                    exerciseImage.setVisibility(View.VISIBLE);
                    arrowImage.setRotation(0);
                }
                else {
                    exerciseImage.setVisibility(View.GONE);
                    arrowImage.setRotation(180);
                }
            }
        });

        exerciseContainer.addView(v);
    }
}