package com.DawidM.SavingDataCar;

import com.DawidM.SavingDataCar.domain.Car;
import com.DawidM.SavingDataCar.domain.Repairs;
import com.DawidM.SavingDataCar.domain.repository.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    WorkDayRepository workDayRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
