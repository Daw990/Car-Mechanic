package com.DawidM.SavingDataCar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long customerId;
    @Column
    private String firstName;
    @Column
    private String SecondName;
    @Column
    private String email;
    @Column
    private String PhoneNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)   // feth eager load all cars
    @JoinColumn
    private List<Car> cars;

    public void add(Car car){
        if(cars == null){
            cars = new ArrayList<>();
        }
        cars.add(car);
    }

}
