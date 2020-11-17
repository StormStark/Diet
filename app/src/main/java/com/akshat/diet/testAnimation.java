package com.akshat.diet;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class testAnimation extends AppCompatActivity {

    Button startAnim;
    View animateMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);

        startAnim = findViewById(R.id.startAnim);
        animateMe = findViewById(R.id.animate);

        startAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateMe.setScaleX(0);
                animateMe.setPivotX(0);
                ObjectAnimator animation = ObjectAnimator.ofFloat(animateMe, "scaleX", 1);
                animation.setDuration(2000);

                animation.setInterpolator(new LinearInterpolator());
                animation.start();
            }
        });
    }
}