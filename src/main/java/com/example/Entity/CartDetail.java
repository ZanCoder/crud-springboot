package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_details")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_amount")
    private double price_amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Override
    public String toString() {
        return "CartDetail{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", price_amount=" + price_amount +
                ", product=" + product +
                ", cart=" + cart +
                '}';
    }
}
