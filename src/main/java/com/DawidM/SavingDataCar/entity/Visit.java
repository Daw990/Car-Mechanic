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
    @Column
    private Long visitId;
    @Column
    private String visitStartedDate;
    @Column
    private String visitDate;
    @Column
    private String visitTime;
    @Column
    private Integer position;  // work position

    @OneToMany
    @JoinColumn
    private List<Customer> customers;

    @OneToMany
    @JoinColumn
    private List<Repair> repairs;

    public void add(Customer customer){
        if(customers == null){
            customers = new ArrayList<>();
        }
        customers.add(customer);
    }

    public void add(Repair repair){
        if(repairs == null){
            repairs = new ArrayList<>();
        }
        repairs.add(repair);
    }
}
