package com.vatril.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by vatril on 14.09.16.
 */
public class Util {

    public static boolean isDouble(String potDouble){
        try {
            Double.parseDouble(potDouble);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static Date getDateFromString(String s){
        String[] tmpar = s.split("-");
        ArrayList<Integer> list = new ArrayList();
        for (String st : tmpar) {
            try {
                list.add(Integer.parseInt(st));
            }catch (Exception e){}
        }
        int[] temp = new int[3];
        temp[0] = list.get(0);
        temp[2] = list.get(1);
        switch (tmpar[1].toLowerCase()){
            case "jan":temp[1] = 0;
                break;
            case "feb":temp[1] = 1;
                break;
            case "mar":temp[1] = 2;
                break;
            case "apr":temp[1] = 3;
                break;
            case "may":temp[1] = 4;
                break;
            case "jun":temp[1] = 5;
                break;
            case "jul":temp[1] = 6;
                break;
            case "aug":temp[1] = 7;
                break;
            case "sep":temp[1] = 8;
                break;
            case "oct":temp[1] = 9;
                break;
            case "nov":temp[1] = 10;
                break;
            case "dec":temp[1] = 11;
                break;
            default: temp[1] = 0;
        }
        return new GregorianCalendar(temp[2],temp[1],temp[0]).getGregorianChange();
    }

    public static int[] getIntFromString(String text) {
        String[] tmpar = text.split(",");
        ArrayList<Integer> list = new ArrayList();
        for (String s : tmpar) {
            try {
                list.add(Integer.parseInt(s));
            }catch (Exception e){}
        }
        int[] temp = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            temp[i] = list.get(i);
        }
        return temp;
    }
}
