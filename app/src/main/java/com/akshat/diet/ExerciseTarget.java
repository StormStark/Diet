package com.akshat.diet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class ExerciseTarget extends Fragment {

    private ExtendedFloatingActionButton startFloat;
    private NestedScrollView nestedScrollView;
    private ArrayList<ExerciseTime> allViews;
    private ArrayList<BreakTime> breakViews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_target, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allViews = new ArrayList<>();
        breakViews = new ArrayList<>();
        ArrayList<StructureExercise> allExercises = new ArrayList<>();
        allExercises = StaticData.addExercise(allExercises);
        Log.d("Mike", allExercises.size() + "");

        Collections.shuffle(allExercises);

        for (int i = 0; i < 5; i++) {
            addLayout(allExercises.get(i));
            if (i != 4)
                addBreak();
        }
        startFloat = view.findViewById(R.id.startFloat);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    startFloat.shrink(); // scroll down
                }
                if (scrollY < oldScrollY) {
                    startFloat.extend(); // scroll up
                }
            }
        });

        startFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allViews.size() < 1)
                    return;

                long delay = 0;

                int i = 0, j = 0;
                while (true) {
                    if (i < allViews.size()) {
                        View v = allViews.get(i).getView();
                        long duration = allViews.get(i).getAnimationDuration();
                        startAnimation(v, duration, delay);
                        delay += duration;
                        i++;
                    } else break;

                    if (j < breakViews.size()) {
                        View v = breakViews.get(j).getView();
                        long duration = breakViews.get(j).getBreakDuration();
                        startAnimation(v, duration, delay);
                        delay += duration;
                        j++;
                    }
                }

            }
        });
    }

    private void startAnimation(final View view, final long duration, long delay) {

        final Timer timer = new Timer();
        View animateMe = view.findViewById(R.id.animate);
        animateMe.setScaleX(0);
        animateMe.setPivotX(0);
        ObjectAnimator animation = ObjectAnimator.ofFloat(animateMe, "scaleX", 1);
        animation.setDuration(duration * 1000);
        animation.setStartDelay(delay * 1000);
        final long[] durationTimer = {duration};
        animation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        durationTimer[0]--;
                        ((TextView) view.findViewById(R.id.exerciseTime)).setText(durationTimer[0] + "s");
                    }
                }, 0, 1000);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                timer.cancel();
            }
        });
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
    }

    private void addLayout(StructureExercise exercise) {

        LinearLayout exerciseContainer = getActivity().findViewById(R.id.exerciseContainer);

        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.exercise_inflator_anim_layout, exerciseContainer, false);

        final ImageView exerciseImage = v.findViewById(R.id.exerciseImage);
        exerciseImage.setImageResource(getResources().getIdentifier(exercise.getImage(), "drawable", "com.akshat.diet"));

        final TextView exerciseName, exerciseDescription, exerciseTimeText;
        exerciseName = v.findViewById(R.id.exerciseName);
        exerciseDescription = v.findViewById(R.id.exerciseDescription);
        exerciseTimeText = v.findViewById(R.id.exerciseTime);

        exerciseName.setText(exercise.getName());
        exerciseDescription.setText(exercise.getDescription());
        exerciseTimeText.setText(exercise.getTime() + "s");

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (exerciseImage.getVisibility() == View.GONE) {
                    exerciseImage.setVisibility(View.VISIBLE);
                } else {
                    exerciseImage.setVisibility(View.GONE);
                }
            }
        });

        ExerciseTime exerciseTime = new ExerciseTime(v, Long.parseLong(exercise.getTime()));
        allViews.add(exerciseTime);
        exerciseContainer.addView(v);
    }

    private void addBreak() {
        LinearLayout exerciseContainer = getActivity().findViewById(R.id.exerciseContainer);

        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.exercise_break_layout, exerciseContainer, false);

        final TextView breakTimeText;
        breakTimeText = v.findViewById(R.id.exerciseTime);

        breakTimeText.setText("40s");

        BreakTime breakTime = new BreakTime(v, 40);
        breakViews.add(breakTime);
        exerciseContainer.addView(v);
    }
}

class ExerciseTime {
    private View view;
    private long animationDuration;

    public ExerciseTime(View view, long animationDuration) {
        this.view = view;
        this.animationDuration = animationDuration;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public long getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
    }
}

class BreakTime {
    private View view;
    private long breakDuration;

    public BreakTime(View view, long animationDuration) {
        this.view = view;
        this.breakDuration = animationDuration;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public long getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(long breakDuration) {
        this.breakDuration = breakDuration;
    }
}