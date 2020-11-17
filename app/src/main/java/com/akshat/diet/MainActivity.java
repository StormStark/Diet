package com.akshat.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView singnUpText;
    private TextInputLayout usernameTextField, passwordTextField;
    private Button loginButton;
    public static final String MyPREFERENCES = "LoginPreference";

    SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singnUpText = findViewById(R.id.headingText);
        loginButton = findViewById(R.id.loginButton);
        usernameTextField = findViewById(R.id.usernameTextField);
        passwordTextField = findViewById(R.id.passwordTextField);

        loginPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Users currentUser = UtilityFunctions.getLoginPreference(loginPreferences);
        Log.d("MIKE", "C " + new Gson().toJson(currentUser));
        if (currentUser != null) {
            Intent i = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(i);
        }

        singnUpText.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        usernameTextField.getEditText().addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usernameTextField.setError(null);
            }
        });

        passwordTextField.getEditText().addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordTextField.setError(null);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.headingText:
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity(i);
                break;

            case R.id.loginButton:
                if (!(UtilityFunctions.inputMinLengthValidation(usernameTextField, 4) && UtilityFunctions.inputMinLengthValidation(passwordTextField, 8)))
                    return;
                String emailValue = usernameTextField.getEditText().getText().toString().trim();
                String passwordValue = passwordTextField.getEditText().getText().toString().trim();
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                Users user = db.getUserBy(emailValue, passwordValue);
                if (user == null) {
                    UtilityFunctions.showSnackbar(view, "Login Failed", Snackbar.LENGTH_LONG);
                    return;
                }
                UtilityFunctions.addLoginPreference(loginPreferences, user);
                UtilityFunctions.showSnackbar(view, "Login Successful", Snackbar.LENGTH_SHORT);
                i = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(i);

                break;
        }
    }
}