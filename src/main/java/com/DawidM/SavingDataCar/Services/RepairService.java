package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.Repair;

import java.util.List;

public interface RepairService {

    public List<Repair> findAll();

    public Repair findById(Long id);

    public Repair save(Repair repair);

    public void deleteById(Long id);
}
