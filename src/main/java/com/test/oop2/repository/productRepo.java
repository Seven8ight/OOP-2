package com.test.oop2.repository;

import com.test.oop2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface productRepo extends JpaRepository<Product, UUID> {}
