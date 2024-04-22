package com.example.controller.admin;

import com.example.dto.CartDetailDto;
import com.example.dto.CartDto;
import com.example.dto.CustomerDto;
import com.example.service.CartDetailService;
import com.example.service.CartService;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {
    private CartService cartService;
    private CartDetailService cartDetailService;
    private CustomerService customerService;

    @Autowired
    public CartController(CartService cartService, CartDetailService cartDetailService, CustomerService customerService) {
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
        this.customerService = customerService;
    }

    @GetMapping("/admin/cart")
    public String getCartList(Model model) {
        List<CartDetailDto> cartDetailDtos = cartDetailService.getAllCartDetails();
        List<CartDto> cartDtos = cartService.getAllCarts();
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        model.addAttribute("cartDtos", cartDtos);
        model.addAttribute("cartDetailDtos", cartDetailDtos);
        model.addAttribute("customerDtos", customerDtos);
        return "admin/page/cart-page";
    }
}
