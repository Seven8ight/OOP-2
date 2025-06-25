package com.test.oop2.dto;

import java.util.UUID;

public class OrderProductInput {
    private UUID productId;
    private int quantity;

    // Getters and Setters
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
