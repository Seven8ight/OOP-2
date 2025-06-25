package com.test.oop2.repository;

import com.test.oop2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface orderRepo extends JpaRepository<Order, UUID> {
    List<Order> findByUserId(UUID userId);
}
