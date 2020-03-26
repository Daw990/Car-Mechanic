package com.DawidM.SavingDataCar.domain;

public class Car {

    private int id;
    private String name;
    private int year;
    private String repairs;

    public Car(){
    }

    public Car(String name, int year, String repairs){
        this.name = name;
        this.year = year;
        this.repairs = repairs;
    }

    public Car(String name, int year){
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRepairs() {
        return repairs;
    }

    public void setRepairs(String repairs) {
        this.repairs = repairs;
    }

    public String toString(){
        return "Nazwa auta: " + name + "\nRok produkcji: " + year + "\nRodzaj naprawy: " + repairs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
