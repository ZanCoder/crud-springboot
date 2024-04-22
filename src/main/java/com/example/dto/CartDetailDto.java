package com.example.dto;

import com.example.Entity.Cart;
import com.example.Entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {
    private Long id;
    private double price;
    private int quantity;
    private double price_amount;
    private Long productId;
    private Long cartId;
}
