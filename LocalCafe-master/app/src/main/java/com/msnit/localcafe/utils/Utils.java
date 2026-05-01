package com.msnit.localcafe.utils;

public class Utils {

    public static int getInt(String number){
        int i;
        try {
            i = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            i = 0;
        }
        return i;
    }
}
