package com.DawidM.SavingDataCar.repository;

import com.DawidM.SavingDataCar.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
