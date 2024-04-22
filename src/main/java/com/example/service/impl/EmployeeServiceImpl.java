package com.example.service.impl;

import com.example.Entity.Category;
import com.example.Entity.Employee;
import com.example.Entity.Product;
import com.example.Entity.Role;
import com.example.dto.EmployeeDto;
import com.example.dto.ProductDto;
import com.example.repository.EmployeeRepository;
import com.example.repository.RoleRepository;
import com.example.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private RoleRepository roleRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    public EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setAvatar(employee.getAvatar());
        employeeDto.setRoles(employee.getRoles());
        return employeeDto;
    }

    public Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setAvatar(employeeDto.getAvatar());
        employee.setRoles(employeeDto.getRoles());
        return employee;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        return mapToDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        // Map DTO to Entity & Save
        Employee employee = mapToEntity(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);

        return mapToDto(saveEmployee);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) {
        return mapToDto(employeeRepository.saveAndFlush(mapToEntity(employeeDto))); // Cách 2 khi save
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Invalid Username or password");
        } else {
            return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(), rolesToAuthorities(employee.getRoles()));
        }
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
