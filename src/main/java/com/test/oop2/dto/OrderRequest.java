package com.test.oop2.dto;

import java.util.List;
import java.util.UUID;

public class OrderRequest {
    private UUID userId;
    private List<OrderProductInput> products;

    // Getters and Setters
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public List<OrderProductInput> getProducts() { return products; }
    public void setProducts(List<OrderProductInput> products) { this.products = products; }
}
