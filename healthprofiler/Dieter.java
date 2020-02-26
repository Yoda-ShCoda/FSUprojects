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
 * This Class allows the creation of the Dieter object with their age, gender, and activity level.
 * The age group and daily min and max calories are determined by these attributes.
 */

public class Dieter {

    private String name;
    private int age;
    private int ageGroup;
    private int height;
    private int weight;
    private int minTargetHeartRate;
    private int maxTargetHeartRate;
    private int maxHeartRate;
    private int BMI;
    private int gender;             //0 is male, 1 is female
    private int activityLevel;      //0 is sedentary, 1 is moderately active, 2 is active
    private int minCalories;
    private int maxCalories;

    /**
     * This is the constructor for the Dieter.
     *
     * @param age
     * @param gender
     * @param activityLevel
     */
    public Dieter(String name, int age, String gender, int height, int weight, String activityLevel) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        maxHeartRate = 220 - age;
        minTargetHeartRate = (int) (0.5 * maxHeartRate);
        maxTargetHeartRate = (int) (0.8 * maxHeartRate);
        BMI = (int) ((weight * 703) / Math.pow(height, 2));

        if(gender.equals("Female"))
            this.gender = 0;
        else
            this.gender = 1;

        if(activityLevel.equals("Sedentary"))
            this.activityLevel = 0;
        else if(activityLevel.equals("Moderately Active"))
            this.activityLevel = 1;
        else
            this.activityLevel = 2;

        setAgeGroup();
        setMinMaxCalories();
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getMinTargetHeartRate() {
        return minTargetHeartRate;
    }

    public int getMaxTargetHeartRate() {
        return maxTargetHeartRate;
    }

    public int getMaxHeartRate() {
        return maxHeartRate;
    }

    public int getBMI() {
        return BMI;
    }

    /**
     * This method returns the Dieter's age
     *
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * This method returns the Dieter's ageGroup
     * @return int
     */
    public int getAgeGroup() {
        return ageGroup;
    }

    /**
     * This method returns the Dieter's gender
     *
     * @return String
     */
    public String getGender() {
        if(gender == 0)
            return "Female";

        return "Male";
    }

    /**
     * This method returns the Dieter's activty level
     *
     * @return String
     */
    public String getActivityLevel() {
        if(activityLevel == 0)
            return "Sedentary";
        else if(activityLevel == 1)
            return "Moderately Active";
        else
            return "Active";
    }

    /**
     * This method returns the Dieter's minimum required daily caloric intake
     *
     * @return int
     */
    public int getMinCalories() {
        return minCalories;
    }

    /**
     * This method returns the Dieter's maximum required daily caloric intake
     *
     * @return
     */
    public int getMaxCalories() {
        return maxCalories;
    }

    /**
     * This method sets the Dieter's age group which is determined by their age.
     */
    private void setAgeGroup() {
        if(age == 2 || age == 3) {
            ageGroup = 0;
            return;
        }
        if(age < 9) {
            ageGroup = 1;
            return;
        }
        if(age < 14) {
            ageGroup = 2;
            return;
        }
        if(age < 19) {
            ageGroup = 3;
            return;
        }
        if(age < 31) {
            ageGroup = 4;
            return;
        }
        if(age < 51) {
            ageGroup = 5;
            return;
        }

        ageGroup = 6;
    }

    /**
     * This method uses the Dieter's age group, gender, and activity level to set the Dieter's
     * daily minimum and maximum calorie allowance
     */
    private void setMinMaxCalories() {
        if(ageGroup == 0 && activityLevel == 0) {
            minCalories = 700;
            maxCalories = 1000;
            return;
        }
        if((ageGroup == 0 && activityLevel == 1) || (ageGroup == 0 && activityLevel == 2)) {
            minCalories = 1000;
            maxCalories = 1400;
            return;
        }
        if(gender == 0 && activityLevel == 0 && ageGroup == 1) {
            minCalories = 840;
            maxCalories = 1200;
            return;
        }
        if(gender == 0 && activityLevel == 0 && ageGroup == 2) {
            minCalories = 1120;
            maxCalories = 1600;
            return;
        }
        if(gender == 0 && activityLevel == 0 && ageGroup == 3) {
            minCalories = 1260;
            maxCalories = 1800;
            return;
        }
        if(gender == 0 && activityLevel == 0 && ageGroup == 4) {
            minCalories = 1400;
            maxCalories = 2000;
            return;
        }
        if(gender == 0 && activityLevel == 0 && ageGroup == 5) {
            minCalories = 1260;
            maxCalories = 1800;
            return;
        }
        if(gender == 0 && activityLevel == 0 && ageGroup == 6) {
            minCalories = 1120;
            maxCalories = 1600;
            return;
        }
        if(gender == 0 && activityLevel == 1 && ageGroup == 1) {
            minCalories = 1400;
            maxCalories = 1600;
            return;
        }
        if(gender == 0 && activityLevel == 1 && ageGroup == 2) {
            minCalories = 1600;
            maxCalories = 2000;
            return;
        }
        if(gender == 0 && activityLevel == 1 && ageGroup == 3) {
            minCalories = 1400;
            maxCalories = 2000;
            return;
        }
        if(gender == 0 && activityLevel == 1 && ageGroup == 4) {
            minCalories = 2000;
            maxCalories = 2200;
            return;
        }
        if(gender == 0 && activityLevel == 1 && ageGroup == 5) {
            minCalories = 1400;
            maxCalories = 2000;
            return;
        }
        if(gender == 0 && activityLevel == 1 && ageGroup == 6) {
            minCalories = 1260;
            maxCalories = 1800;
            return;
        }
        if(gender == 0 && activityLevel == 2 && ageGroup == 1) {
            minCalories = 1400;
            maxCalories = 1800;
            return;
        }
        if(gender == 0 && activityLevel == 2 && ageGroup == 2) {
            minCalories = 1800;
            maxCalories = 2200;
            return;
        }
        if(gender == 0 && activityLevel == 2 && ageGroup == 3) {
            minCalories = 1680;
            maxCalories = 2400;
            return;
        }
        if(gender == 0 && activityLevel == 2 && ageGroup == 4) {
            minCalories = 1680;
            maxCalories = 2400;
            return;
        }
        if(gender == 0 && activityLevel == 2 && ageGroup == 5) {
            minCalories = 1540;
            maxCalories = 2200;
            return;
        }
        if(gender == 0 && activityLevel == 2 && ageGroup == 6) {
            minCalories = 2000;
            maxCalories = 2200;
            return;
        }
        if(gender == 1 && activityLevel == 0 && ageGroup == 1) {
            minCalories = 980;
            maxCalories = 1400;
            return;
        }
        if(gender == 1 && activityLevel == 0 && ageGroup == 2) {
            minCalories = 1260;
            maxCalories = 1800;
            return;
        }
        if(gender == 1 && activityLevel == 0 && ageGroup == 3) {
            minCalories = 1540;
            maxCalories = 2200;
            return;
        }
        if(gender == 1 && activityLevel == 0 && ageGroup == 4) {
            minCalories = 1680;
            maxCalories = 2400;
            return;
        }
        if(gender == 1 && activityLevel == 0 && ageGroup == 5) {
            minCalories = 1540;
            maxCalories = 2200;
            return;
        }
        if(gender == 1 && activityLevel == 0 && ageGroup == 6) {
            minCalories = 1400;
            maxCalories = 2000;
            return;
        }
        if(gender == 1 && activityLevel == 1 && ageGroup == 1) {
            minCalories = 1400;
            maxCalories = 1600;
            return;
        }
        if(gender == 1 && activityLevel == 1 && ageGroup == 2) {
            minCalories = 1800;
            maxCalories = 2200;
            return;
        }
        if(gender == 1 && activityLevel == 1 && ageGroup == 3) {
            minCalories = 2400;
            maxCalories = 2800;
            return;
        }
        if(gender == 1 && activityLevel == 1 && ageGroup == 4) {
            minCalories = 2600;
            maxCalories = 2800;
            return;
        }
        if(gender == 1 && activityLevel == 1 && ageGroup == 5) {
            minCalories = 2400;
            maxCalories = 2600;
            return;
        }
        if(gender == 1 && activityLevel == 1 && ageGroup == 6) {
            minCalories = 2200;
            maxCalories = 2400;
            return;
        }
        if(gender == 1 && activityLevel == 2 && ageGroup == 1) {
            minCalories = 1600;
            maxCalories = 2000;
            return;
        }
        if(gender == 1 && activityLevel == 2 && ageGroup == 2) {
            minCalories = 2000;
            maxCalories = 2600;
            return;
        }
        if(gender == 1 && activityLevel == 2 && ageGroup == 3) {
            minCalories = 2800;
            maxCalories = 3200;
            return;
        }
        if(gender == 1 && activityLevel == 2 && ageGroup == 4) {
            minCalories = 2100;
            maxCalories = 3000;
            return;
        }
        if(gender == 1 && activityLevel == 2 && ageGroup == 5) {
            minCalories = 2800;
            maxCalories = 3000;
            return;
        }
        if(gender == 1 && activityLevel == 2 && ageGroup == 6) {
            minCalories = 2400;
            maxCalories = 2800;
            return;
        }
    }
}

