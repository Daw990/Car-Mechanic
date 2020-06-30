package com.DawidM.SavingDataCar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repair {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long repairId;
    @Column
    private String fault;
    @Column
    private Integer repairTime;
    @Column
    private Integer repairPrice;
}
