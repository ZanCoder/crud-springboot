package com.example.service;

import com.example.dto.CartDetailDto;

import java.util.List;

public interface CartDetailService {
    List<CartDetailDto> getAllCartDetails();
    CartDetailDto getCartDetailById(Long id);
}
