package com.DawidM.SavingDataCar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repair {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idRepair;
    @Column
    private String fault;
    @Column
    private Integer repairTime;
    @Column
    private Integer repairPrice;
    @Column
    private String category;
}
