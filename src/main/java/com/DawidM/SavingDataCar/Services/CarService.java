package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.entity.Car;

import java.util.List;

public interface CarService {

    public List<Car> findAll();

    public Car findById(long id);

    public Car save(Car car);

    public void deleteById(long id);
}
