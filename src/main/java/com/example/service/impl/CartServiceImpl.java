package com.example.service.impl;

import com.example.Entity.Cart;
import com.example.dto.CartDto;
import com.example.repository.CartRepository;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }



    public Cart mapToEntity(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setCreateOn(cartDto.getCreateOn());
        cart.setStatus(cartDto.getStatus());
        cart.setCustomer(cartRepository.findById(cartDto.getCustomerId()).get().getCustomer());
        return cart;
    }

    public CartDto mapToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setCreateOn(cart.getCreateOn());
        cartDto.setStatus(cart.getStatus());
        cartDto.setCustomerId(cart.getCustomer().getId());
        return cartDto;
    }

    @Override
    public List<CartDto> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDto> cartDtos = carts.stream().map(cart -> mapToDto(cart)).collect(Collectors.toList());
        return cartDtos;
    }

    @Override
    public CartDto getCartById(Long id) {
        return mapToDto(cartRepository.findById(id).get());
    }
}
