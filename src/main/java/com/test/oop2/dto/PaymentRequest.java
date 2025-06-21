package com.test.oop2.dto;

import java.util.UUID;

public class PaymentRequest {
    private UUID userId;
    private UUID orderId;
    private String branch;

    // Getters and setters
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
}
