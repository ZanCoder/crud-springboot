package com.example.repository;

import com.example.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Truy vấn theo tên
    @Query("SELECT s FROM Employee s WHERE s.name LIKE %:name%")
    List<Employee> findByName(@Param("name") String name);

    // Security
    Employee findByUsername(String username);
}
