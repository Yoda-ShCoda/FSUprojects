package com.danit.healthprofiler;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.util.ArrayList;

/**
 * Danit You
 * 3/19/2018
 * CSCI325 - Mobile Application Development
 * Project I
 *
 * This project provides a mobile application for a user to generate a daily meal plan that has
 * a total calorie content that falls within their recommended range.  This range is determined by
 * the age, gender, and activity level of the user.
 */

/**
 * This program provides EditText, Spinner, and RadioButton fields for a user to enter their
 * age, gender, and activity level.  Once the Confirm button is clicked, this information is passed
 * to the CreateMealActivity and the CreateMealActivity screen is displayed
 */
public class MainActivity extends AppCompatActivity {

    EditText Name, Age, Height, Weight;
    Spinner Gender;
    RadioGroup ActivityLevel;
    RadioButton sedentary, moderatelyActive, active, selected;
    int age, height, weight;
    String name, gender, activityLevel;
    Button Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.nameEditText);
        Age = findViewById(R.id.ageEditText);
        Height = findViewById(R.id.heightEditText);
        Weight = findViewById(R.id.weightEditText);
        Gender = findViewById(R.id.genderSpinner);
        ActivityLevel = findViewById(R.id.activityLevelRadioGroup);
        sedentary = findViewById(R.id.sedentaryRadioButton);
        moderatelyActive = findViewById(R.id.moderatelyActiveRadioButton);
        active = findViewById(R.id.activeRadioButton);
        Gender = findViewById(R.id.genderSpinner);
        Confirm = findViewById((R.id.confirmButton));

        populateSpinner();
        setConfirmButtonListener();
    }

    /**
     * This method populates the Spinner with the Strings Male and Female
     */
    public void populateSpinner() {
        ArrayList<String> ary = new ArrayList<>();

        ary.add("Male");
        ary.add("Female");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ary);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Gender.setAdapter(adapter);
    }

    /**
     * This method attaches a ClickListener to the Confirm button. If the entry into the age field
     * is invalid, an error message will display.  If all input is valid, the CreateMealActivity
     * screen will display and the user entered information is passed to this activity.
     */
    public void setConfirmButtonListener() {
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!NumberCheckUtil.checkIfStringIsNum(Age.getText().toString())) {
                    displayInvalidNumberAlert(1);
                    return;
                }

                age = Integer.parseInt(Age.getText().toString());

                if(age < 2) {
                    displayInvalidNumberAlert(1);
                    return;
                }

                if(!NumberCheckUtil.checkIfStringIsNum(Height.getText().toString())) {
                    displayInvalidNumberAlert(2);
                    return;
                }

                if(!NumberCheckUtil.checkIfStringIsNum(Weight.getText().toString())) {
                    displayInvalidNumberAlert(3);
                    return;
                }

                name = Name.getText().toString();
                height = Integer.parseInt(Height.getText().toString());
                weight = Integer.parseInt(Weight.getText().toString());
                gender = Gender.getSelectedItem().toString();
                selected = findViewById(ActivityLevel.getCheckedRadioButtonId());
                activityLevel = selected.getText().toString();

                Intent confirm = new Intent(MainActivity.this, StatsDisplayActivity.class);
                confirm.putExtra("name", name);
                confirm.putExtra("age", age);
                confirm.putExtra("gender", gender);
                confirm.putExtra("activityLevel", activityLevel);
                confirm.putExtra("height", height);
                confirm.putExtra("weight", weight);
                startActivity(confirm);
            }
        });
    }

    /**
     * This method displays an error message telling the user the age they entered is invalid and to
     * reenter a valid age, height, or weight.
     */
    public void displayInvalidNumberAlert(int i) {
        String invalidMessage = "";

        if(i == 1)
            invalidMessage = "PLEASE ENTER A VALID NUMBER, 2 OR ABOVE, FOR YOUR AGE!!!";
        else if(i == 2)
            invalidMessage = "PLEASE ENTER A VALID HEIGHT IN INCHES!!!";
        else
            invalidMessage = "PLEASE ENTER A VALID WEIGHT IN POUNDS!!!";

        //build error box
        AlertDialog.Builder newDialog = new AlertDialog.Builder(MainActivity.this);
        newDialog.setMessage(invalidMessage).setCancelable(false)
                .setPositiveButton("GOT IT!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        //create alert box
        AlertDialog alert = newDialog.create();
        alert.setTitle("ERROR");
        alert.show();
    }
}