package com.test.oop2.repository;

import com.test.oop2.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepo extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findByOrderId(UUID orderId);
    List<OrderItem> findByProductId(UUID productId); // Optional
    List<OrderItem> findByBranchId(UUID branchId);
}
