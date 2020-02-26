package com.danit.healthprofiler;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
 * This Class provides a database of food items.  Each Food object is organized by its meal and
 * foodgroup attribute for easy retrieval.
 */
public class FoodDB {

    private HashMap<String, HashMap<String, ArrayList<Food>>> foodDB;
    private String[] meals;
    private String[] foodGroups;
    private Context context;

    /**
     * This is the constructor for the FoodDB object.  The file containing the list of properly
     * formatted food items (meal, foodgroup, calorie content), is provided as an asset from the
     * Context creating the FoodDB object.  String arrays of the meals and foodgroups are populated
     * and the HashMap (DB) is populated with food items.
     *
     * @param context
     * @throws IOException
     */
    public FoodDB(Context context) throws IOException {
        this.context = context;
        createFoodDB();

    }

    public void createFoodDB() throws IOException {
        foodDB = new HashMap<>();
        meals = new String[] {"breakfast", "lunch", "dinner"};
        foodGroups = new String[] {"grains", "meats", "veggies", "fruits", "dairy", "fatsOilsSweets"};
        setKeysAndValues();
        loadFoodDB();
    }

    /**
     * This method sets the keys of the HashMap foodDB as the meal names and the values as a new HashMap.
     * The keys of the inner HashMap are set to the food group names and the values are ArrayLists
     * of Food objects
     */
    private void setKeysAndValues() {
        for(int i = 0; i < meals.length; i++) {
            foodDB.put(meals[i], new HashMap<String, ArrayList<Food>>());

            for(int j = 0; j < foodGroups.length; j++) {
                foodDB.get(meals[i]).put(foodGroups[j], new ArrayList<Food>());
            }
        }
    }

    /**
     * This method takes a file provided by the Context's assets, and creates Food objects from
     * each line of information.  The Food objects are then loaded into the HashMap into their
     * appropriate locations
     *
     * @throws IOException
     */
    private void loadFoodDB() throws IOException {
        String meal, foodGroup, foodName, servingSize;
        int calorieContent;

        AssetManager assetManager = context.getAssets();
        Scanner in = new Scanner(assetManager.open("Food Info.txt"));


        while (in.hasNextLine()) {
            meal = in.next();
            foodGroup = in.next();
            foodName = in.next();
            calorieContent = in.nextInt();
            servingSize = in.next();

            loadFood(new Food(meal, foodGroup, foodName, calorieContent, servingSize));

            if(in.hasNextLine())
                in.nextLine();
        }
    }

    /**
     * This is a helper method for the loadFoodDB method.  It inserts Food objects into their proper
     * location, by meal and food group, in the HashMap.
     *
     * @param food
     */
    private void loadFood(Food food) {
        foodDB.get(food.getMeal()).get(food.getFoodGroup()).add(food);
    }

    /**
     * This method takes the provided parameters and returns the specified Food object at the index
     * in the ArrayList.
     *
     * @param meal
     * @param foodGroup
     * @param idx
     *
     * @return Food
     */
    public Food getFood(String meal, String foodGroup, int idx) {
        return foodDB.get(meal).get(foodGroup).get(idx);

    }

    /**
     * This method returns the amount of Food objects in the ArrayList in the HashMap specified by
     * the meal and food group
     *
     * @param meal
     * @param foodGroup
     *
     * @return int
     */
    public int getSize(String meal, String foodGroup) {
        return foodDB.get(meal).get(foodGroup).size();
    }

    /**
     * This method retrieves a String[] of the meals.
     *
     * @return String[]
     */
    public String[] getMeals() {
        return meals;
    }

    /**
     * This method retrieves a String[] of the food groups
     *
     * @return String[]
     */
    public String[] getFoodGroups() {
        return foodGroups;
    }
}
