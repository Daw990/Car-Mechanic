package com.DawidM.SavingDataCar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idVisit;
    @Column
    private String visitStartedDate;
    @Column
    private String visitDate;
    @Column
    private LocalTime visitTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}  )
    @JoinColumn(name="id_car")
    private Car car;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name="id_repair")
    private Repair repair;


}
