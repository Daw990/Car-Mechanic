package com.DawidM.SavingDataCar.domain.repository;

import com.DawidM.SavingDataCar.domain.Car;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class WorkDayRepository {

    Map<Integer, Car> cars = new HashMap<>();

    public WorkDayRepository(){
    }

    public void createCar(int year, String nameCar, String fault){
        Car newCar = new Car(nameCar, year, fault);
        newCar.setId(getNewId());
        cars.put(newCar.getId(), newCar);
    }

    public void createCar(Car car){
        car.setId(getNewId());
        cars.put(car.getId(), car);
    }

    public void editCar(Car car){

    }

    public Optional<Car> getCar(String name){
         Optional<Car> carByName = cars.values().stream().filter(car -> car.getName().equals(name)).findAny();

        return carByName;
    }

    public int getNewId() {
        if(cars.isEmpty()){
            return 0;
        }
        else{
            Integer maxKey = Collections.max(cars.keySet());
            return maxKey + 1;
        }
    }

    public Collection<Car> getallCars(){
        return cars.values();
    }

    public void deleteCar(Integer id){
        cars.remove(id);
    }

    public Car getCarById(Integer id) {

        return cars.get(id);
    }

    @PostConstruct
    public void build(){
        createCar(2001, "Skoda", "wymiana oleju");
        createCar(2010,"Audi", "klocki tarcze");
    }

    @Override
    public String toString() {
        return "WorkDayRepository{" +
                "cars=" + cars +
                '}';
    }

    public void updadeCar(int id, Car car) {
        cars.put(id, car);
    }
}
