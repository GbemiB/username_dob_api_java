package com.recruitment.vgs.api.util;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DateToCalender {
    public Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }
}
