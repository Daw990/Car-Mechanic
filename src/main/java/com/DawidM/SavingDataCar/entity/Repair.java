package com.DawidM.SavingDataCar.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Repair {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long repairId;
    @Column
    private String fault;
    @Column
    private Integer repairTime;
    @Column
    private Integer repairPrice;
}
