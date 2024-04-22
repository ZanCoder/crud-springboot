package com.example.service;

import com.example.Entity.Employee;
import com.example.dto.EmployeeDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto update(EmployeeDto employeeDto);

    void deleteEmployeeById(Long id);

    List<Employee> findByName(String name);

    Employee findByUsername(String username);
}
