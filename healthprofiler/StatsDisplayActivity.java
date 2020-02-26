package com.danit.healthprofiler;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatsDisplayActivity extends AppCompatActivity {

    int age, height, weight;
    String name, gender, activityLevel;
    Dieter dieter;
    TextView StatsDisplay;
    HealthProfileDB healthProfileDB;
    Button BackButton, Meal;
    Cursor cursor;
    Long ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_display);

        StatsDisplay = findViewById(R.id.statsDisplayTextView);
        BackButton = findViewById(R.id.backButton);
        Meal = findViewById(R.id.meal);

        Bundle extras = getIntent().getExtras();

        name = extras.getString("name");
        age = extras.getInt("age");
        gender = extras.getString("gender");
        height = extras.getInt("height");
        weight = extras.getInt("weight");
        activityLevel = extras.getString("activityLevel");

        //instantiate dieter
        dieter = new Dieter(name, age, gender, height, weight, activityLevel);

        //instantiate DB
        healthProfileDB = new HealthProfileDB(this, "", null, 1);

        //Insert and store ID of current entry for comparison with previous stats entry
        ID = healthProfileDB.insert_stats(dieter);

        displayStats();

        //If not first entry, compare to previous entry
        if(ID > 1)
            compareStats();

        setBackBtnClickListener();
        setMealClickListener();
    }

    //Display health stats in TextView
    public void displayStats() {
        //Cursor to read all entries in database.  To select only a certain number of entries,
        //append WHERE clause to argument, e.g. ("SELECT * FROM DIETER_STATS WHERE ID > " + (id - x), null)
        //where x is number of entries to be listed
        Cursor cursor = healthProfileDB.getReadableDatabase().rawQuery("SELECT * FROM DIETER_STATS", null);

        StatsDisplay.setText("");

        while (cursor.moveToNext()) {
            StatsDisplay.append("ID: " + cursor.getInt(0) + ";  ");
            StatsDisplay.append("AGE: " + cursor.getInt(1) + ";  ");
            StatsDisplay.append("HEIGHT: " + cursor.getInt(2) + ";  ");
            StatsDisplay.append("WEIGHT: " + cursor.getInt(3) + ";  ");
            StatsDisplay.append("BMI: " + cursor.getInt(4) + ";  ");
            StatsDisplay.append("MIN TARGET: " + cursor.getInt(5) + ";  ");
            StatsDisplay.append("MAX TARGET: " + cursor.getInt(6) + ";  ");
            StatsDisplay.append("MAX RATE: " + cursor.getInt(7) + "\n");
        }
    }

    //This method is to compare the current health data with the previous entry
    public void compareStats() {
        //Cursor to read previous entry from database
        cursor = healthProfileDB.getReadableDatabase().rawQuery("SELECT BMI, WEIGHT FROM DIETER_STATS WHERE ID =" + (ID - 1), null);

        int previousBMI;
        int previousWeight;

        cursor.moveToNext();

        previousBMI = cursor.getInt(0);
        previousWeight = cursor.getInt(1);
        StatsDisplay.append("PREVIOUS BMI: " + previousBMI);
        StatsDisplay.append("; PREVIOUS WEIGHT: " + previousWeight + "\n");

        displayProgressPopUp(previousBMI, previousWeight);
    }

    //This method is to display the progress
    public void displayProgressPopUp(int previousBMI, int previousWeight) {
        String results;

        if(previousBMI < dieter.getBMI())
            results = "YOUR BMI WENT UP. BAD JOB!!!";
        else if(previousBMI > dieter.getBMI())
            results = "YOUR BMI WENT DOWN. GOOD JOB!!!";
        else
            results = "YOUR BMI IS THE SAME!!!";

        String bmiDescription = String.format("\n\nYou're current BMI of %d indicates that you are %s.",
                dieter.getBMI(), describeBMI(dieter.getBMI()));

        AlertDialog.Builder newDialog = new AlertDialog.Builder(StatsDisplayActivity.this);
        newDialog.setMessage(results + bmiDescription).setCancelable(false)
                .setPositiveButton("GOT IT!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        //create alert box
        AlertDialog alert = newDialog.create();
        alert.setTitle("RESULTS");
        alert.show();
    }

    //This method returns the description of a BMI level
    public String describeBMI(int BMI) {
        if(BMI < 25)
            return "normal";
        if(BMI < 30)
            return "overweight";
        if(BMI < 40)
            return "obese";
        else
            return "extremely obese";
    }

    public void setBackBtnClickListener() {
        BackButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(StatsDisplayActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        }));
    }

    public void setMealClickListener() {
        Meal.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meal = new Intent(StatsDisplayActivity.this, CreateMealActivity.class);
                meal.putExtra("name", name);
                meal.putExtra("age", age);
                meal.putExtra("gender", gender);
                meal.putExtra("activityLevel", activityLevel);
                meal.putExtra("height", height);
                meal.putExtra("weight", weight);
                startActivity(meal);
            }
        }));
    }
}
