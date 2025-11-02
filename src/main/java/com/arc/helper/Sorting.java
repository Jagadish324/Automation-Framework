package com.arc.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {
    public static String[] sortTime(String [] dateTimes){
        Arrays.sort(dateTimes, Comparator.comparing(Sorting::parseDateTime));
        return dateTimes;
    }
    private static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
        LocalDateTime dateAndTime = null;
        try {
            dateAndTime = LocalDateTime.parse(dateTime, formatter);
        }catch (DateTimeParseException e){
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            dateAndTime = LocalDateTime.parse(dateTime, formatter);
        }
        return dateAndTime;
    }
}
