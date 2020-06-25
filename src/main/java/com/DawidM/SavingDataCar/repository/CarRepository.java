package com.DawidM.SavingDataCar.repository;

import com.DawidM.SavingDataCar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
