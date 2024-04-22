package com.example.controller.admin;

import com.example.dto.CustomerDto;
import com.example.dto.OrderDetailDto;
import com.example.dto.OrderDto;
import com.example.service.CustomerService;
import com.example.service.OrderDetailService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private CustomerService customerService;
    private ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, OrderDetailService orderDetailService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/admin/order")
    public String listOrder(Model model) {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        List<OrderDetailDto> orderDetailDtos = orderDetailService.getAllOrderDetails();
        model.addAttribute("orders", orderDtos);
        model.addAttribute("customers", customerDtos);
        model.addAttribute("orderDetails", orderDetailDtos);
        return "/admin/page/order-page";
    }
}
