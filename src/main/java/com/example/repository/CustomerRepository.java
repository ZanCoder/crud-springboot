package com.example.repository;

import com.example.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Truy vấn theo tên
    @Query("SELECT s FROM Customer s WHERE s.name LIKE %:name%")
    List<Customer> findByName(@Param("name") String name);

    // Security
    Customer findByUsername(String username);
}
