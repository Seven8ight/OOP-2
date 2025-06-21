package com.test.oop2.controller;

import com.test.oop2.model.Order;
import com.test.oop2.model.OrderItem;
import com.test.oop2.model.OrderStatus;
import com.test.oop2.model.Product;
import com.test.oop2.repository.OrderRepo;
import com.test.oop2.repository.ProductRepo;
import com.test.oop2.dto.OrderRequest;
import com.test.oop2.dto.OrderProductInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private ProductRepo productRepository;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (OrderProductInput item : orderRequest.getProducts()) {
            UUID productId = item.getProductId();
            int quantity = item.getQuantity();

            Optional<Product> productOpt = productRepository.findById(productId);
            if (productOpt.isEmpty()) {
                return "Product with ID " + productId + " not found.";
            }

            Product product = productOpt.get();

            if (quantity > product.getQuantity()) {
                return "Insufficient stock for product: " + product.getName();
            }

            double price = product.getPrice() * quantity;
            total += price;

            // Deduct product stock
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            OrderItem orderItem = new OrderItem(productId, quantity, price, null);
            orderItems.add(orderItem);
        }

        // Create the order
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setTotalPrice(total);
        order.setOrderStatus(OrderStatus.PENDING);  // ✅ Set status


        for (OrderItem item : orderItems) {
            item.setOrder(order); // Set the back-reference
        }

        order.setItems(orderItems);
        orderRepository.save(order);

        return "Order placed successfully.";
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable UUID userId) {
        return orderRepository.findByUserId(userId);
    }
}
