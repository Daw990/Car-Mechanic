package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public Customer findById(Long id);

    public Customer save(Customer customer);

    public void deleteById(Long id);
}
