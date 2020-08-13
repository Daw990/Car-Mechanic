package com.DawidM.SavingDataCar.custom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    public static String getDateAndTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
    public static String getDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getDatePlus2Days(){
        LocalDateTime dateNow = LocalDateTime.now();
        dateNow.plusDays(2);
        dateNow.isBefore(dateNow);
        return dateNow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static LocalDate dateStringToLocalDate(String date){

        return LocalDate.parse(date);
    }
}
