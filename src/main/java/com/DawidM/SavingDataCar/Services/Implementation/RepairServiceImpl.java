package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.Services.RepairService;
import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.Repair;
import com.DawidM.SavingDataCar.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RepairServiceImpl implements RepairService {

    RepairRepository repairRepository;

    @Autowired
    public RepairServiceImpl(RepairRepository repairRepository){
        this.repairRepository = repairRepository;
    }

    @Override
    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    @Override
    public Repair findById(Long id) {
        Optional<Repair> result = repairRepository.findById(id);

        Repair repair = null;

        if (result.isPresent()) {
            repair = result.get();
        }
        else {
            // we didn't find the repair
            throw new RuntimeException("Did not find repair id - " + id);
        }
        return repair;
    }

    @Override
    public Repair save(Repair repair) {
        return repairRepository.save(repair);
    }

    @Override
    public void deleteById(Long id) {
        repairRepository.deleteById(id);
    }

    @Override
    public List<Repair> findByCategory(String category) {
        return repairRepository.findByCategory(category);
    }
}
