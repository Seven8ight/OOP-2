package com.test.oop2.controller;

import com.test.oop2.dto.PaymentRequest;
import com.test.oop2.model.*;
import com.test.oop2.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private orderRepo orderRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PaymentRepo paymentRepository;

    @PostMapping
    public String makePayment(@RequestBody PaymentRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        Optional<Order> orderOpt = orderRepository.findById(request.getOrderId());

        if (userOpt.isEmpty()) return "User not found.";
        if (orderOpt.isEmpty()) return "Order not found.";

        User user = userOpt.get();
        Order order = orderOpt.get();

        if (!order.getUserId().equals(user.getId())) {
            return "This order does not belong to the user.";
        }

        if (order.getOrderStatus() == OrderStatus.SUCCESSFUL) {
            return "Order already paid.";
        }

        double total = order.getTotalPrice();

        if (user.getBalance() < total) {
            return "Not enough balance to make the payment.";
        }

        // Deduct from balance
        user.setBalance(user.getBalance() - total);
        userRepository.save(user);

        // Update order status
        order.setOrderStatus(OrderStatus.SUCCESSFUL);
        orderRepository.save(order);

        // Log the payment
        Payment payment = new Payment();
        payment.setUserId(user.getId());
        payment.setOrderId(order.getId());
        payment.setAmount(total);
        payment.setStatus(OrderStatus.SUCCESSFUL);
        payment.setBranch(request.getBranch());

        paymentRepository.save(payment);

        return "Payment successful. Order marked as PAID.";
    }

    // ✅ Place these extra routes BELOW your makePayment() method

    @GetMapping("/history/user/{userId}")
    public List<Payment> getPaymentHistory(@PathVariable UUID userId) {
        return paymentRepository.findByUserId(userId);
    }

    @GetMapping("/history/user/{userId}/branch/{branch}")
    public List<Payment> getPaymentsByUserAndBranch(
            @PathVariable UUID userId,
            @PathVariable String branch
    ) {
        return paymentRepository.findByUserIdAndBranch(userId, branch);
    }

    @GetMapping("/history/branch/{branch}")
    public List<Payment> getPaymentsByBranch(@PathVariable String branch) {
        return paymentRepository.findByBranch(branch);
    }
}
