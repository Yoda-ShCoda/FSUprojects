package com.danit.healthprofiler;

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
 * This Class allows the creation of a Food object with its associated meal, name, food group, and
 * caloric content as its attributes.
 */

public class Food {

    private String meal, foodGroup, foodName, servingSize;
    private int calorieContent;

    /**
     * This is the constructor for the Food object
     *
     * @param meal
     * @param foodGroup
     * @param foodName
     * @param calorieContent
     */
    public Food(String meal, String foodGroup, String foodName, int calorieContent, String servingSize) {
        this.meal = meal;
        this.foodGroup = foodGroup;
        this.foodName = foodName;
        this.calorieContent = calorieContent;
        this.servingSize = servingSize;
    }

    /**
     * This method retrieves the meal associated with the food
     *
     * @return String
     */
    public String getMeal() {
        return meal;
    }

    /**
     * This method retrieves the food group associated with the food
     *
     * @return String
     */
    public String getFoodGroup() {
        return foodGroup;
    }

    /**
     * This method retrieves the name of he food
     *
     * @return String
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * This method retrieves the caloric content of the food.
     *
     * @return int
     */
    public int getCalorieContent() {
        return calorieContent;
    }

    public String getServingSize() {
        return servingSize;
    }
}
