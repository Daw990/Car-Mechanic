package com.DawidM.SavingDataCar.custom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    public static String getDateAndTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public static String customFormatDate(LocalDateTime localDateTime){

        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
