package com.recruitment.vgs.api.util;

import java.util.Date;

public class DateDiffCalculator {

    public static long calculateDays (Date first, Date second) throws Exception{

              long time_difference = first.getTime() - second.getTime();

            long days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;

            long dayDiff = days_difference;

        return dayDiff;

    }
}
