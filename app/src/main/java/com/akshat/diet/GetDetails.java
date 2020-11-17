package com.akshat.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class GetDetails extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout genderTextField, nameTextField, ageTextField, weightTextField, heightTextField;
    private Button saveDetailsBtn;
    private String[] genders = {"Male", "Female", "Others"};
    private String username, fullName, password;
    public static final String MyPREFERENCES = "LoginPreference";

    SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        username = getIntent().getExtras().getString("username");
        password = getIntent().getExtras().getString("password");
        fullName = getIntent().getExtras().getString("fullName");

        saveDetailsBtn = findViewById(R.id.saveDetailsBtn);
        genderTextField = findViewById(R.id.genderTextField);
        nameTextField = findViewById(R.id.nameTextField);
        ageTextField = findViewById(R.id.ageTextField);
        weightTextField = findViewById(R.id.weightTextField);
        heightTextField = findViewById(R.id.heightTextField);

        loginPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        nameTextField.getEditText().setText(fullName);
        ArrayAdapter genderAdapter = new ArrayAdapter<String>(this,
                R.layout.list_layout, genders);

        ((AutoCompleteTextView) genderTextField.getEditText()).setAdapter(genderAdapter);

        saveDetailsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveDetailsBtn:
                if (detailValidation()) {
                    Users user = new Users();
                    user.setFullName(fullName);
                    user.setPassword(password);
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(ageTextField.getEditText().getText().toString()));
                    user.setUserWeightInKG(weightTextField.getEditText().getText().toString());
                    user.setUserHeightInCM(heightTextField.getEditText().getText().toString());
                    user.setGender(genderTextField.getEditText().getText().toString());
                    DatabaseHelper db = new DatabaseHelper(GetDetails.this);
                    db.addNewUser(user);
                    UtilityFunctions.addLoginPreference(loginPreferences, user);
                    Intent i = new Intent(GetDetails.this, NavigationActivity.class);
                    startActivity(i);
                }
                break;
        }
    }

    private boolean detailValidation() {
        if (UtilityFunctions.inputMinLengthValidation(ageTextField, 1) &&
                UtilityFunctions.inputMinLengthValidation(genderTextField, 2) &&
                UtilityFunctions.inputMinLengthValidation(weightTextField, 2) &&
                UtilityFunctions.inputMinLengthValidation(heightTextField, 2)
        ) {
            return true;
        }
        return false;
    }
}