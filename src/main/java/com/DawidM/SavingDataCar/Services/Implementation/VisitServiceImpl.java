package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.Services.VisitService;
import com.DawidM.SavingDataCar.entity.Visit;
import com.DawidM.SavingDataCar.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class VisitServiceImpl implements VisitService {

    VisitRepository visitRepository;

    @Autowired
    VisitServiceImpl(VisitRepository visitRepository){
        this.visitRepository = visitRepository;
    }


    @Override
    public List<Visit> getVisitsByDate(String date) {
        return visitRepository.getVisitByDate(date);
    }

    @Override
    public List<LocalTime> getAllHours(){

        LocalTime startingTime = LocalTime.of(8,0);
        List<LocalTime> listTimes =
                Stream.iterate(startingTime.truncatedTo(ChronoUnit.MINUTES), t -> t.plusMinutes(30L))
                        .limit(16)
                        .collect(Collectors.toList());

        return listTimes;
    }

    @Override
    public Map<LocalTime, Integer> fillHoursMap(){

        Map<LocalTime, Integer> hoursMap = new LinkedHashMap<>();
        LocalTime startingTime = LocalTime.of(8,0);

        long addMinutes = 0;

        for(int i = 16; i > 0; i--){

            hoursMap.put(startingTime.plusMinutes(addMinutes), i);
            addMinutes+=30;
        }
        return hoursMap;
    }

    @Override
    public Map<LocalTime, Integer> changeValuesInHoursMap(Map<LocalTime, Integer> hoursMap,
                                                          int repairTime, LocalTime startingHourRepair){

        long addMinutes = 0;
        for(int i = 0; repairTime > i; i++){
            hoursMap.put(startingHourRepair.plusMinutes(addMinutes), 0);
            addMinutes+=30;
        }

        hoursMap.forEach((k,v) -> {
            if(v == 0){
                LocalTime secondTime = k;

                int i = 1;
                long minutes = 30;

                if(hoursMap.get(secondTime) == 0) {

                    while ( true ) {
                        if(secondTime == LocalTime.of(8,0)) break;

                        secondTime = secondTime.minusMinutes(minutes);
                        if(hoursMap.get(secondTime) == 0) break;

                        LocalTime localTime = LocalTime.now();

                            hoursMap.put(secondTime, i);

                        i++;
                    }
                }
            }
        });
        return hoursMap;
    }

    @Override
    public List<LocalTime> getFreeHoursInList(Map<LocalTime, Integer> hoursMap, int repairTime, String date){

        List<LocalTime> hoursList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate2 = LocalDate.parse(date, formatter);

        hoursMap.forEach((k,v) -> {
            if(localDate2.equals(LocalDate.now())) {
                if(k.isAfter(LocalTime.now())) {
                    if (v >= repairTime) {
                        hoursList.add(k);
                    }
                }
            }else{
                if (v >= repairTime) {
                    hoursList.add(k);
                }
            }
        });
        return hoursList;
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }
}
