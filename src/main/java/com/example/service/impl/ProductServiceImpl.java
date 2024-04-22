package com.example.service.impl;

import com.example.Entity.Category;
import com.example.Entity.Product;
import com.example.dto.ProductDto;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    public static final int PRODUCT_PER_PAGE = 10;

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> mapToDto(product));
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return mapToDto(product);
    }
    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        // Map DTO to Entity & Save
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepository.save(product);

        // Lấy Id trong Category từ ProductDto lưu vào Product
        Category category = new Category();
        category.setId(productDto.getCategoryId());
        product.setCategory(category);

        return mapToDto(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {
        // Map DTO to Entity & Save
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepository.saveAndFlush(product);

        // Lấy Id trong Category từ ProductDto lưu vào Product
        Category category = new Category();
        category.setId(productDto.getCategoryId());
        product.setCategory(category);

        return mapToDto(savedProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Page<ProductDto> findByName(String name, Pageable pageable) {
        Page<Product> productPage = productRepository.findByName(name, pageable);
        Page<ProductDto> productDtos = productPage.map(product -> mapToDto(product));
        return productDtos;
    }

    @Override
    public List<Product> getAllProductsSortedByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Product> getAllProductsSortedByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }


    public Product mapToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setImage(productDto.getImage());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
        return product;
    }

    public ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setQuantity(product.getQuantity());
        productDto.setImage(product.getImage());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }
}
