package com.DawidM.SavingDataCar.repository;

import com.DawidM.SavingDataCar.entity.Repair;
import com.DawidM.SavingDataCar.entity.User;
import com.DawidM.SavingDataCar.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("select i from Visit i where i.visitDate=:date")
    List<Visit> getVisitByDate(@Param("date") String date);


}
