package com.example.service.impl;

import com.example.Entity.Order;
import com.example.dto.OrderDto;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return mapToDto(orderRepository.findById(id).get());
    }

    public Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setCustomer(customerRepository.findById(orderDto.getCustomerId()).get());
        return order;
    }

    public OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setCustomerId(order.getCustomer().getId());
        return orderDto;
    }
}
