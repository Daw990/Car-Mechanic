package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.entity.Visit;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface VisitService {

    List<Visit> getVisitsByDate(String date);
    Visit save(Visit visit);
    List<LocalTime> getAllHours();
    Map<LocalTime, Integer> fillHoursMap();
    Map<LocalTime, Integer> changeValuesInHoursMap(Map<java.time.LocalTime, java.lang.Integer> hoursMap,
                                                int repairTime, java.time.LocalTime startingHourRepair);
    List<LocalTime> getFreeHoursInList(Map<LocalTime, Integer> hoursMap, int repairTime);

    }
