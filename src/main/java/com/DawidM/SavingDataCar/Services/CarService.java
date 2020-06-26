package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.entity.Car;

import java.util.List;

public interface CarService {

    public List<Car> findAll();

    public Car findById(Long id);

    public Car save(Car car);

    public void deleteById(Long id);
}
