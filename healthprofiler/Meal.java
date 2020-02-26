package com.danit.healthprofiler;

import java.util.Random;

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
 * This class allows the creation of a meal object.  The meal is created using the Dieter min and max
 * calories as the guideline for total calorie content of the meal and the food items for the
 * specified meal are obtained from the FoodDB. Each food group is represented in the meal
 */
public class Meal {

    private Food[] mealItems;
    private int mealCalorieContent;
    String[] foodGroups;

    /**
     * This is the constructor for the meal object.  The createMeal method is called to create the meal
     * using the dieter, meal, and foodDB as parameters.
     *
     * @param dieter
     * @param meal
     * @param foodDB
     */
    public Meal(Dieter dieter, String meal, FoodDB foodDB) {
        mealItems = new Food[foodDB.getFoodGroups().length];
        foodGroups = foodDB.getFoodGroups();

        createMeal(dieter, meal, foodDB);
    }

    /**
     * This method selects a food item from each food group for a specific meal. The method is
     * recursively called until the meal's total calorie content falls with the range of the
     * Dieter's daily min and max calorie allowance divided by three.
     *
     * @param dieter
     * @param meal
     * @param foodDB
     */
    private void createMeal(Dieter dieter, String meal, FoodDB foodDB) {
        Random rng = new Random();
        int idx;
        mealCalorieContent = 0;

        //add food items from each foodgroup into meal array
        for(int i = 0; i < foodGroups.length; i++) {
            idx = rng.nextInt(foodDB.getSize(meal, foodGroups[i]));
            mealItems[i] = foodDB.getFood(meal, foodGroups[i], idx);
            mealCalorieContent += mealItems[i].getCalorieContent();
        }

        //Is not within range of min and max caloies divided by 3, createMeal is called again
        if(mealCalorieContent < dieter.getMinCalories()/3)
            createMeal(dieter, meal, foodDB);
        if(mealCalorieContent > dieter.getMaxCalories()/3)
            createMeal(dieter, meal, foodDB);
    }

    /**
     * This method returns the calorie content of the meal
     *
     * @return int
     */
    public int getMealCalorieContent() {
        return mealCalorieContent;
    }

    /**
     * This method returns the Food item in the Meal specified by its food group
     *
     * @param foodGroup
     * @return Food
     */
    public Food getMealItem(String foodGroup) {
        for(int i = 0; i < mealItems.length; i++)
            if(mealItems[i].getFoodGroup().equals(foodGroup))
                return mealItems[i];

        return null;
    }

    public Food[] getMeal() {
        return mealItems;
    }
}
