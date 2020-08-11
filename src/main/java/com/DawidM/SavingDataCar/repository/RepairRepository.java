package com.DawidM.SavingDataCar.repository;

import com.DawidM.SavingDataCar.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {

    @Query("select i from Repair i where i.category=:category")
    List<Repair> findRepairByCategory(@Param("category")String category);

    List<Repair> findByCategory(String category); //querry from metod name
}
