package com.test.oop2.controller;

import com.test.oop2.model.Product;
import com.test.oop2.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepository;

    // Create
    @PostMapping
    public String addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "Product added successfully.";
    }

    // Read All
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Read One
    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable UUID id) {
        return productRepository.findById(id);
    }

    // Update
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable UUID id, @RequestBody Product newProduct) {
        return productRepository.findById(id).map(product -> {
            if (newProduct.getName() != null) product.setName(newProduct.getName());
            if (newProduct.getDescription() != null) product.setDescription(newProduct.getDescription());
            if (newProduct.getQuantity() != 0) product.setQuantity(newProduct.getQuantity());
            if (newProduct.getPrice() != 0.0) product.setPrice(newProduct.getPrice());
            productRepository.save(product);
            return "Product updated.";
        }).orElse("Product not found.");
    }


    // Delete
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product deleted.";
        } else {
            return "Product not found.";
        }
    }
}
