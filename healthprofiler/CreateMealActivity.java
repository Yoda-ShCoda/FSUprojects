package com.danit.healthprofiler;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;

/**
 * This program takes the age, gender, and activity level provided by the MainActivity and creates
 * a Dieter object.  A FoodDB object is then created.  The user is able to click any one of the three
 * meal buttons and generate a meal of Food items from the FoodDB that falls within their daily
 * caloric intake requirements. If a user does not like the meal displayed, they have the option to
 * create a new meal by simply clicking the corresponding meal button. The items in each meal and
 * caloric content are displayed.  Also, a simple analysis is done on the bottom of the screen for
 * the user to view.
 */
public class CreateMealActivity extends AppCompatActivity {

    TextView breakfastTV, lunchTV, dinnerTV;
    TextView BreakfastCalories, LunchCalories, DinnerCalories;
    Button Breakfast;
    Button Lunch;
    Button Dinner;
    FoodDB foodDB;
    TextView Analysis;
    int age, height, weight;
    String name, gender, activityLevel;
    Dieter dieter;
    Button backBtn;
    Meal breakfast, lunch, dinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);

        //retrieve values from MainActivity
        Bundle extras = getIntent().getExtras();

        name = extras.getString("name");
        age = extras.getInt("age");
        gender = extras.getString("gender");
        height = extras.getInt("height");
        weight = extras.getInt("weight");
        activityLevel = extras.getString("activityLevel");

        //instantiate dieter
        dieter = new Dieter(name, age, gender, height, weight, activityLevel);

        //instantiate food database object
        try {
            foodDB = new FoodDB(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Analysis = findViewById(R.id.analysisTextView);
        backBtn = findViewById((R.id.backBtn));

        initializeBreakfast();
        initializeLunch();
        initializeDinner();

        setBreakfastBtnClickListener();
        setLunchBtnClickListener();
        setDinnerBtnClickListener();
        setBackBtnClickListener();

    }

    /**
     * This method initializes the TextView and Button objects in the Breakfast section of the UI
     */
    public void initializeBreakfast() {
        Breakfast = findViewById(R.id.breakfastButton);
        breakfastTV = findViewById(R.id.breakfastTextView);
        BreakfastCalories = findViewById(R.id.breakfastCaloriesTextView);
    }

    /**
     * This method initializes the TextView and Button objects in the Lunch section of the UI
     */
    public void initializeLunch() {
        Lunch = findViewById(R.id.lunchButton);
        lunchTV = findViewById(R.id.lunchTextView);
        LunchCalories = findViewById(R.id.lunchCaloriesTextView);
    }

    /**
     * This method initializes the TextView and Button objects in the Dinner section of the UI
     */
    public void initializeDinner() {
        Dinner = findViewById(R.id.dinnerButton);
        dinnerTV = findViewById(R.id.dinnerTextView);
        DinnerCalories = findViewById(R.id.dinnerCaloriesTextView);
    }

    /**
     * This method attaches a ClickListener object to the Breakfast Button.  A Meal object is
     * created and the contents of the meal and calorie content of the meal are displayed
     */
    public void setBreakfastBtnClickListener() {
        Breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakfast = new Meal(dieter, "breakfast", foodDB);
                Food[] mealItems = breakfast.getMeal();

                StringBuffer foods = new StringBuffer();

                //append the names of food in meal to foods
                for(int i = 0; i < mealItems.length; i++) {
                    foods.append(mealItems[i].getFoodName() + "-" + mealItems[i].getServingSize() + "; ");
                    //foods.append(breakfast.getMealItem(foodDB.getFoodGroups()[i]).getFoodName() + "  ");
                }

                breakfastTV.setText(foods.toString());

                BreakfastCalories.setText(breakfast.getMealCalorieContent() + "");

                display();
            }
        });
    }

    /**
     * This method attaches a ClickListener object to the Lunch Button.  A Meal object is
     * created and the contents of the meal and calorie content of the meal are displayed
     */
    public void setLunchBtnClickListener() {
        Lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lunch = new Meal(dieter, "lunch", foodDB);
                Food[] mealItems = lunch.getMeal();

                StringBuffer foods = new StringBuffer();

                //append the names of food in meal to foods
                for(int i = 0; i < foodDB.getFoodGroups().length; i++) {
                    foods.append(mealItems[i].getFoodName() + "-" + mealItems[i].getServingSize() + "; ");
                    //foods.append(lunch.getMealItem(foodDB.getFoodGroups()[i]).getFoodName() + "  ");
                }

                lunchTV.setText(foods.toString());

                LunchCalories.setText(lunch.getMealCalorieContent() + "");

                display();
            }
        });
    }

    /**
     * This method attaches a ClickListener object to the Breakfast Button.  A Meal object is
     * created and the contents of the meal and calorie content of the meal are displayed
     */
    public void setDinnerBtnClickListener() {
        Dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dinner = new Meal(dieter, "dinner", foodDB);
                Food[] mealItems = dinner.getMeal();

                StringBuffer foods = new StringBuffer();

                //append the names of food in meal to foods
                for(int i = 0; i < foodDB.getFoodGroups().length; i++) {
                    foods.append(mealItems[i].getFoodName() + "-" + mealItems[i].getServingSize() + "; ");
                    //foods.append(dinner.getMealItem(foodDB.getFoodGroups()[i]).getFoodName() + "  ");
                }

                dinnerTV.setText(foods.toString());

                DinnerCalories.setText(dinner.getMealCalorieContent() + "");

                display();
            }
        });
    }

    /**
     * This method displays the range of calories a specific Dieter should be consuming a day and the
     * total calories for the meals currently displayed on the screen.
     */
    public void display() {
        int totalDailyCalorieIntake = Integer.parseInt(BreakfastCalories.getText().toString()) +
                Integer.parseInt(LunchCalories.getText().toString()) + Integer.parseInt(DinnerCalories.getText().toString());
        Analysis.setText(String.format("A %d-year old, %s %s should be consuming anywhere from" +
                        " %d to %d calories a day.  Your current meal plan has you at %d calories!",
                dieter.getAge(), dieter.getActivityLevel().toLowerCase(), dieter.getGender().toLowerCase(),
                dieter.getMinCalories(), dieter.getMaxCalories(), totalDailyCalorieIntake));
    }

    /**
     * This method attaches a ClickListener to the Back button.  Once clicked, the screen returns to
     * the MainActivity where user enters their information.
     */
    public void setBackBtnClickListener() {
        backBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(CreateMealActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        }));
    }

    /**
     * This method provides the ability to send an email.  It takes the user String input as email
     * address and the meal items for each meal as the message body.
     *
     * @param view
     */
    public void emailDailyMealPlan(View view) {
        StringBuilder messageSubject = new StringBuilder();
        StringBuilder messageBody = new StringBuilder();
        int totalDailyCalorieIntake = Integer.parseInt(BreakfastCalories.getText().toString()) +
                Integer.parseInt(LunchCalories.getText().toString()) + Integer.parseInt(DinnerCalories.getText().toString());
        EditText emailAddress = findViewById(R.id.emailEditText);

        //email subject
        messageSubject.append("Yooooooo");

        //building email body
        messageBody.append(String.format("A %d-year old, %s %s should be consuming anywhere from" +
                " %d to %d calories a day.  Your current meal plan has you at %d calories!",
                dieter.getAge(), dieter.getActivityLevel().toLowerCase(), dieter.getGender().toLowerCase(),
                dieter.getMinCalories(), dieter.getMaxCalories(), totalDailyCalorieIntake)); //Analysis.getText().toString());
        messageBody.append("\n\nThis is your breakfast:\n" + breakfastTV.getText() + "\nThis meal has a total " +
                "of " + BreakfastCalories.getText().toString() + " calories");
        messageBody.append("\n\nThis is your lunch:\n" + lunchTV.getText() + "\nThis meal has a total " +
                "of " + LunchCalories.getText().toString() + " calories");
        messageBody.append("\n\nThis is your dinner:\n" + dinnerTV.getText() + "\nThis meal has a total " +
                "of " + DinnerCalories.getText().toString() + " calories");
        messageBody.append("\n\nThis meal plan totals " + totalDailyCalorieIntake + " calories");

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String[] to = {emailAddress.getText().toString()};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, messageSubject.toString());
        intent.putExtra(Intent.EXTRA_TEXT, messageBody.toString());
        intent.setType("message/rfc822");
        startActivity(intent);
    }
}

