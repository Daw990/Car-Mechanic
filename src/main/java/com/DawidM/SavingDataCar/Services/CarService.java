package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.domain.Car;
import com.DawidM.SavingDataCar.domain.repository.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarService {

    @Autowired
    WorkDayRepository repository;

    public void updateCar(Car car) {
        repository.updadeCar(car.getId(), car);
    }

    public List<Car> getAllCars(){
        return new ArrayList<>(repository.getallCars());
    }

    public void saveCar(Car car) {
        repository.createCar(car);
    }

//    public void editCar(Car car){
//        repository.
//    }

    public Car getCar(Integer id){

        return repository.getCarById(id);
    }

    public void deleteCar(Integer id) {
        repository.deleteCar(id);
    }
}
