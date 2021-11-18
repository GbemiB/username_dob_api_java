package com.recruitment.vgs.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDiffCalculator {

    public static Long calculateDays (String first, String second) throws ParseException {

        SimpleDateFormat obj = new SimpleDateFormat("YYYY-MM-DD");


            Date date1 = obj.parse(first);
            Date date2 = obj.parse(second);

            long time_difference = date2.getTime() - date1.getTime();

            long days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;

            Long dayDiff = days_difference;

        return dayDiff;

    }
}
