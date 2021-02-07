package com.patrikduch.springboot_aws_api.utils;

import java.util.ArrayList;

/**
 * A {@link StringConversionUtil} helper that provides arbitrary conversion between String data structures.
 * @author Patrik Duch
 */
public class StringConversionUtil {

    /**
     * Conversion of ArrayList<String> to String[].
     * @return String[] result object.
     */
    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }
}