package com.arc.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateAndTime {
    public static String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        return dateFormat.format(todayDate);
    }
    public static String getFutureDate(int noOfFutureDay){
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, noOfFutureDay);
        Date futureDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        return dateFormat.format(futureDate);
    }
    public static String getTodaysDateWithDesiredFormat(String desired_Format)
    {
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat(desired_Format);
        String todaysDate=dateFormat.format(date);
        System.out.println("Desired format : "+desired_Format);
        System.out.println("Todays date in desired format : _________"+todaysDate);
        return todaysDate;
    }
    public static String getFutureDateWithDesiredFormat(String desired_Format,int noOfFutureDay)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, noOfFutureDay);
        Date futureDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat(desired_Format);
        return dateFormat.format(futureDate);
    }
    public static String getCurrentTimeStamp()
    {
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMyyHHmmss");
        String currentTime=dateFormat.format(date);
        return currentTime;
    }
    public static String changeDateFormatToDesiredDateFormat(String desiredDateFormat, String actualDateFormat, String dateToFormat)
    {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(actualDateFormat, Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(desiredDateFormat,Locale.ENGLISH);
        Date date = null;
        try
        {
            date = simpleDateFormat1.parse(dateToFormat);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return simpleDateFormat2.format(date);
    }

    public static String getCurrentDateMMMdd(){
        Calendar calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(todayDate);
    }
    public static String getCurrentShortDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentTimeStampInDesiredFormat(String format) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String currentTime = dateFormat.format(date);
        return currentTime;
    }


    public static String getMonthNumber(String monthName) {
        try {
            Month month = Month.valueOf(monthName.toUpperCase());
            int monthInt = month.getValue();
            return String.format("%02d", monthInt); // Format the month number as two digits
        } catch (IllegalArgumentException e) {
            return "Invalid month name";
        }
    }










}
