package com.example.repository;

import com.example.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Truy vấn theo tên
    @Query("SELECT s FROM Category s WHERE s.name LIKE %:name%")
    List<Category> findByName(@Param("name") String name);
}
