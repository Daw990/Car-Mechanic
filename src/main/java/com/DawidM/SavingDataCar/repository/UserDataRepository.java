package com.DawidM.SavingDataCar.repository;

import com.DawidM.SavingDataCar.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
