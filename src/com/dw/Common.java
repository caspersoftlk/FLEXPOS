package com.dw;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Common {
    public static String formatNumber(double number){
        return String.format("%,.2f",number);
    }
    public static double deFormatNumber(String number){
        if(number.isEmpty())
            return 0;
        else if(!number.contains(","))
            return Double.parseDouble(number);
        else {
            return Double.parseDouble(number.replace(",",""));
        }

    }
}
