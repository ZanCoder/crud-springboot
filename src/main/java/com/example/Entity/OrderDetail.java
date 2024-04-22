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
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "discount")
    private int discount;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", product=" + product +
                ", order=" + order +
                '}';
    }
}
