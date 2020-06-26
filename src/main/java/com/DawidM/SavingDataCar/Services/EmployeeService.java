package com.DawidM.SavingDataCar.Services;

import com.DawidM.SavingDataCar.entity.Car;
import com.DawidM.SavingDataCar.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(Long id);

    public Employee save(Employee employee);

    public void deleteById(Long id);
}
