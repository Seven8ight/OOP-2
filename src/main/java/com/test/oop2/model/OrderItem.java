package com.test.oop2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID productId;
    private UUID branchId;
    private UUID userId;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference // skips this during serialization
    private Order order;

    public OrderItem() {}

    public OrderItem(UUID productId, int quantity, double price, Order order) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }

    public UUID getBranchId() { return branchId; }
    public void setBranchId(UUID branchId) { this.branchId = branchId; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
