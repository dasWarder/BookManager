package com.babichev.bookmanager.util;

public class StringConverter {

    public static String parseDescription(String str, String replaced) {
        String result = str;
        result = str.replaceAll(replaced.trim(), "");
        return result;
    }

    //Method for splitting the string to valid for searching,
    //if the string is complicated(i.e. The Hobbit)
    //we need to transform it to "The+Hobbit"
    public static String getSplittedString(String str) {
        String[] splitted = str.split(" ");
        StringBuilder builder = new StringBuilder();

        for(String part : splitted) {
            builder.append(part + "+");
        }

        return builder.toString();
    }
}
