package com.jsc.coronavirusdetails.Utils;

import java.text.DecimalFormat;

public class MaintainNumber {
    public static String getRoundOffValue(double value){

        DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0");
        return df.format(value);
    }

    public static String getRoundOffValue(int value){


        DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0");
        return df.format(value);
    }
}
