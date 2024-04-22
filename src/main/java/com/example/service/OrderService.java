package com.example.service;

import com.example.Entity.Order;
import com.example.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
}
