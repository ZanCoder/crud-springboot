package com.example.service.impl;

import com.example.Entity.CartDetail;
import com.example.dto.CartDetailDto;
import com.example.repository.CartDetailRepository;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;

    @Autowired
    public CartDetailServiceImpl(CartDetailRepository cartDetailRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public CartDetail mapToEntity(CartDetailDto cartDetailDto) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(cartDetailDto.getId());
        cartDetail.setPrice(cartDetailDto.getPrice());
        cartDetail.setQuantity(cartDetailDto.getQuantity());
        cartDetail.setPrice_amount(cartDetailDto.getPrice_amount());
        cartDetail.setProduct(productRepository.findById(cartDetailDto.getProductId()).get());
        cartDetail.setCart(cartRepository.findById(cartDetailDto.getCartId()).get());
        return cartDetail;
    }

    public CartDetailDto mapToDto(CartDetail cartDetail) {
        CartDetailDto cartDetailDto = new CartDetailDto();
        cartDetailDto.setId(cartDetail.getId());
        cartDetailDto.setPrice(cartDetail.getPrice());
        cartDetailDto.setQuantity(cartDetail.getQuantity());
        cartDetailDto.setPrice_amount(cartDetail.getPrice_amount());
        cartDetailDto.setProductId(cartDetail.getProduct().getId());
        cartDetailDto.setCartId(cartDetail.getCart().getId());
        return cartDetailDto;
    }

    @Override
    public List<CartDetailDto> getAllCartDetails() {
        List<CartDetail> cartDetails = cartDetailRepository.findAll();
        List<CartDetailDto> cartDetailDtos = cartDetails.stream().map(cartDetail -> mapToDto(cartDetail)).collect(Collectors.toList());
        return cartDetailDtos;
    }

    @Override
    public CartDetailDto getCartDetailById(Long id) {
        return mapToDto(cartDetailRepository.findById(id).orElse(null));
    }
}
