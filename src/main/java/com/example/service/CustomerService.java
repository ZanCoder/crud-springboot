package com.example.service;


import com.example.Entity.Customer;
import com.example.dto.CustomerDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto save(CustomerDto customerDto);

    CustomerDto update(CustomerDto customerDto);

    void delete(Long id);

    List<Customer> searchCustomer(String name);

    Customer findByUsername(String username);
}
