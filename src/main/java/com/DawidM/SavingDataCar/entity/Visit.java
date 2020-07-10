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
public class Visit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idVisit;
    @Column
    private String visitStartedDate;
    @Column
    private String visitDate;
    @Column
    private String visitTime;
    @Column
    private Integer position;  // this firm have 2 repairs positions

}
