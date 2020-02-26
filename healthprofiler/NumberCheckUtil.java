package com.danit.healthprofiler;

/*
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
 * This class is a provides a utility to check whether or not a String is a decimal number.
 */

public class NumberCheckUtil {

    /**
     * This method checks to see if a string is a decimal number
     *
     * @param str
     *
     * @return boolean
     */
    public static boolean checkIfStringIsNum(String str) {
        if(str.equals(""))
            return false;

        if(checkDecimals(str) == false)
            return false;


        for(int i = 0; i < str.length(); i++)
            if(checkChar(str.charAt(i)) == false)
                return false;

        return true;
    }

    /**
     * This method checks to verify there is no more than one decimal point in the string and
     * that if the string is of length one, the character is not a decimal point
     *
     * @param str
     * @return
     */
    private static boolean checkDecimals(String str) {
        if(str.length() == 1 && str.charAt(0) == '.')
            return false;

        int count = 0;

        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == '.')
                count++;

        if(count > 1)
            return false;

        return true;
    }

    /**
     * This method checks if a character is a numerical digit or a decimal point. Returns false
     * if neither
     *
     * @param a
     * @return boolean
     */
    private static boolean checkChar(char a) {
        switch(a) {
            case '1':   return true;
            case '2':   return true;
            case '3':   return true;
            case '4':   return true;
            case '5':   return true;
            case '6':   return true;
            case '7':   return true;
            case '8':   return true;
            case '9':   return true;
            case '0':   return true;
            case '.':   return true;
            default:    return false;
        }
    }
}
