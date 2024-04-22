package com.example.repository;

import com.example.Entity.Product;
import com.example.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();
    // Truy vấn theo tên sản phẩm
    @Query("SELECT s FROM Product s WHERE s.name LIKE %:name%")
    Page<Product> findByName(@Param("name") String name, Pageable pageable);
}
