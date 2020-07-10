package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.Services.CarService;
import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarServiceImpl implements CarService {

    CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        Optional<Car> result = carRepository.findById(id);

        Car car = null;

        if (result.isPresent()) {
            car = result.get();
        }
        else {
            // we didn't find the car
            throw new RuntimeException("Did not find car id - " + id);
        }
        return car;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAuthenticatedUserCars(long idUser) {

        return carRepository.getAuthenticatedUserCars(idUser);
    }
}
