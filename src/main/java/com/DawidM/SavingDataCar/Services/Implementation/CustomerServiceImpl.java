package com.DawidM.SavingDataCar.Services.Implementation;

import com.DawidM.SavingDataCar.Services.CustomerService;
import com.DawidM.SavingDataCar.entity.Customer;
import com.DawidM.SavingDataCar.entity.Employee;
import com.DawidM.SavingDataCar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {

        Optional<Customer> result = customerRepository.findById(id);

        Customer customer = null;

        if (result.isPresent()) {
            customer = result.get();
        }
        else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + id);
        }
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
