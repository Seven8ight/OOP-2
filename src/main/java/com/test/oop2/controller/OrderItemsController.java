package com.test.oop2.controller;

import com.test.oop2.model.OrderItem;
import com.test.oop2.repository.OrderItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemRepo orderItemRepository;

    // Get all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get order items by Order ID
    @GetMapping("/order/{orderId}")
    public List<OrderItem> getItemsByOrderId(@PathVariable UUID orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    // Optional: Get order items by Product ID (if needed)
    @GetMapping("/product/{productId}")
    public List<OrderItem> getItemsByProductId(@PathVariable UUID productId) {
        return orderItemRepository.findByProductId(productId);
    }

    @GetMapping("/branch/{branchid}")
    public List<OrderItem> getItemsByBranchId(@PathVariable UUID branchId) {
        return orderItemRepository.findByBranchId(branchId);
    }

}
