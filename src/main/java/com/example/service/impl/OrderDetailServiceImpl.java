package com.example.service.impl;

import com.example.Entity.Order;
import com.example.Entity.OrderDetail;
import com.example.dto.OrderDetailDto;
import com.example.dto.OrderDto;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private ProductRepository productRepository;
    private OrderDetailRepository orderDetailRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderDetail mapToEntity(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDto.getId());
        orderDetail.setPrice(orderDetailDto.getPrice());
        orderDetail.setDiscount(orderDetailDto.getDiscount());
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setProduct(productRepository.findById(orderDetailDto.getProductId()).get());
        orderDetail.setOrder(orderRepository.findById(orderDetailDto.getOrderId()).get());
        return orderDetail;
    }

    public OrderDetailDto mapToDto(OrderDetail orderDetail) {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setId(orderDetail.getId());
        orderDetailDto.setPrice(orderDetail.getPrice());
        orderDetailDto.setDiscount(orderDetail.getDiscount());
        orderDetailDto.setQuantity(orderDetail.getQuantity());
        orderDetailDto.setProductId(orderDetail.getProduct().getId());
        orderDetailDto.setOrderId(orderDetail.getOrder().getId());
        return orderDetailDto;
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<OrderDetailDto> orderDetailDtos = orderDetails.stream().map(orderDetail -> mapToDto(orderDetail)).collect(Collectors.toList());
        return orderDetailDtos;
    }

    @Override
    public OrderDetailDto getOrderDetailById(Long id) {
        return mapToDto(orderDetailRepository.findById(id).get());
    }
}
