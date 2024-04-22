package com.example.service;

import com.example.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getAllOrderDetails();
    OrderDetailDto getOrderDetailById(Long id);
}
