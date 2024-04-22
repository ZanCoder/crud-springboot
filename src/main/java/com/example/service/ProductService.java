package com.example.service;

import com.example.Entity.Product;
import com.example.dto.ProductDto;
import com.example.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    Page<ProductDto> getAllProducts(Pageable pageable);

    ProductDto getProductById(Long id);

    ProductDto save(ProductDto productDto);

    ProductDto update(ProductDto productDto);

    void deleteProductById(Long id);

    Page<ProductDto> findByName(String name, Pageable pageable);

    List<Product> getAllProductsSortedByPriceAsc();

    List<Product> getAllProductsSortedByPriceDesc();
}
