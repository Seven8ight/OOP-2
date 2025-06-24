package com.test.oop2.controller;

import com.test.oop2.model.Order;
import com.test.oop2.model.OrderItem;
import com.test.oop2.model.OrderStatus;
import com.test.oop2.model.Product;
import com.test.oop2.repository.orderRepo;
import com.test.oop2.repository.productRepo;
import com.test.oop2.dto.OrderProductInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private orderRepo orderRepository;

    @Autowired
    private productRepo productRepository;

    @PostMapping
    public ResponseEntity<?> createEmptyOrder(@RequestBody Map<String, UUID> body) {
        UUID userId = body.get("userId");

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalPrice(0.0);
        order.setItems(new ArrayList<>());

        orderRepository.save(order);

        return ResponseEntity.ok(order); // Includes the ID for client
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<?> addItemsToOrder(@PathVariable UUID orderId, @RequestBody List<OrderProductInput> items) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Order not found.");
        }

        Order order = orderOpt.get();
        double total = order.getTotalPrice();
        List<OrderItem> newItems = new ArrayList<>();

        for (OrderProductInput input : items) {
            Optional<Product> productOpt = productRepository.findById(input.getProductId());
            if (productOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Product not found: " + input.getProductId());
            }

            Product product = productOpt.get();

            if (input.getQuantity() > product.getQuantity()) {
                return ResponseEntity.status(400).body("Insufficient stock for " + product.getName());
            }

            double subtotal = product.getPrice() * input.getQuantity();
            product.setQuantity(product.getQuantity() - input.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem(product.getId(), input.getQuantity(), subtotal, order);
            newItems.add(orderItem);

            total += subtotal;
        }

        order.getItems().addAll(newItems);
        order.setTotalPrice(total);
        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }


    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable UUID orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Order not found.");
        }

        orderRepository.deleteById(orderId);
        return ResponseEntity.ok("Order deleted successfully.");
    }

}
