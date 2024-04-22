package com.example.service;

import com.example.dto.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> getAllCarts();
    CartDto getCartById(Long id);
}
