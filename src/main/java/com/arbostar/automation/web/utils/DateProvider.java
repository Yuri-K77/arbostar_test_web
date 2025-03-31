package com.arbostar.automation.web.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateProvider {

    public static String getCurrentDateTime(String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, new Locale("ru"));
        ZonedDateTime zonedDateTimeLocal = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        return zonedDateTimeLocal.format(dateTimeFormatter);
    }

//    public static void main(String[] args) {
//        System.out.println(" ".isEmpty());
//    }
}