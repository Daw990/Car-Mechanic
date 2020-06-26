package com.DawidM.SavingDataCar.repository;

import com.DawidM.SavingDataCar.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}
