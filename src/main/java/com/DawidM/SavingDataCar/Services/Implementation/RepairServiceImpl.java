package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.Services.RepairService;
import com.DawidM.SavingDataCar.entity.Repair;
import com.DawidM.SavingDataCar.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepairServiceImpl implements RepairService {

    RepairRepository repairRepository;

    @Autowired
    public RepairServiceImpl(RepairRepository repairRepository){
        this.repairRepository = repairRepository;
    }

    @Override
    public List<Repair> findAll() {
        return null;
    }

    @Override
    public Repair findById(Long id) {
        return null;
    }

    @Override
    public Repair save(Repair repair) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
