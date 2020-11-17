package com.akshat.diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button signupButton;
    TextInputLayout fname, lname, username, password, confirmPassword;
    TextView loginText;
    ImageView goBackImaage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fname = findViewById(R.id.fnameTextField);
        lname = findViewById(R.id.lnameTextField);
        username = findViewById(R.id.usernameTextField);
        password = findViewById(R.id.passwordTextField);
        confirmPassword = findViewById(R.id.confirmPasswordTextField);
        signupButton = findViewById(R.id.signupButton);
        loginText = findViewById(R.id.loginText);
        goBackImaage = findViewById(R.id.goBackImage);

        loginText.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        goBackImaage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goBackImage:
            case R.id.loginText:
                onBackPressed();
                break;

            case R.id.signupButton:
                minLengthValidation();
                break;
        }
    }

    private boolean minLengthValidation() {
        String usernameValue = username.getEditText().getText().toString().trim();
        String passwordValue = password.getEditText().getText().toString().trim();
        String confirmPasswordValue = confirmPassword.getEditText().getText().toString().trim();
        String fnameValue = fname.getEditText().getText().toString().trim();
        String lnameValue = lname.getEditText().getText().toString().trim();

        if (UtilityFunctions.inputMinLengthValidation(fname, 3) &&
                UtilityFunctions.inputMinLengthValidation(lname, 3) &&
                UtilityFunctions.inputMinLengthValidation(username, 4) &&
                UtilityFunctions.inputMinLengthValidation(password, 8) &&
                UtilityFunctions.inputMinLengthValidation(confirmPassword, 8)
        ) {
            if (!passwordValue.equals(confirmPasswordValue)) {
                confirmPassword.setError("Password mismatch");
                return false;
            }
            confirmPassword.setError(null);

            Intent i = new Intent(Registration.this, GetDetails.class);
            i.putExtra("username", usernameValue);
            i.putExtra("password", passwordValue);
            i.putExtra("fullName", fnameValue + " " + lnameValue);
            startActivity(i);

            return true;
        }
        return false;
    }
}