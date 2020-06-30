package com.DawidM.SavingDataCar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long carId;
    @Column
    private String markCar;
    @Column
    private String modelCar;
    @Column
    private Integer year;
}
