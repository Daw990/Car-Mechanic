package com.DawidM.SavingDataCar.domain;

public class Repairs {

    private String fault;

    public Repairs (String fault){
        this.fault = fault;
    }

    @Override
    public String toString(){
        return fault;
    }
}
