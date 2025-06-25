package com.test.oop2.dto;

import java.util.UUID;

public class InventoryRequest {
    private UUID branchId;
    private UUID productId;
    private int quantity;

    public UUID getBranchId() { return branchId; }
    public void setBranchId(UUID branchId) { this.branchId = branchId; }

    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
