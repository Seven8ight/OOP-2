package com.test.oop2.controller;

import com.test.oop2.dto.InventoryRequest;
import com.test.oop2.model.*;
import com.test.oop2.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private BranchRepo branchRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private BranchInventoryRepo branchInventoryRepository;

    // ✅ Add or Update Inventory
    @PostMapping("/add")
    public String addOrUpdateInventory(@RequestBody InventoryRequest request) {
        Optional<Branch> branchOpt = branchRepository.findById(request.getBranchId());
        Optional<Product> productOpt = productRepository.findById(request.getProductId());

        if (branchOpt.isEmpty()) return "Branch not found.";
        if (productOpt.isEmpty()) return "Product not found.";

        Branch branch = branchOpt.get();
        Product product = productOpt.get();

        Optional<BranchInventory> inventoryOpt =
                branchInventoryRepository.findByBranchAndProduct(branch, product);

        BranchInventory inventory;

        if (inventoryOpt.isPresent()) {
            // Update existing inventory
            inventory = inventoryOpt.get();
            inventory.setQuantity(inventory.getQuantity() + request.getQuantity());
        } else {
            // Create new inventory record
            inventory = new BranchInventory();
            inventory.setBranch(branch);
            inventory.setProduct(product);
            inventory.setQuantity(request.getQuantity());
        }

        branchInventoryRepository.save(inventory);

        return "Inventory updated successfully.";
    }

    @PutMapping("/update")
    public String updateInventoryQuantity(@RequestBody InventoryRequest request) {
        Optional<Branch> branchOpt = branchRepository.findById(request.getBranchId());
        Optional<Product> productOpt = productRepository.findById(request.getProductId());

        if (branchOpt.isEmpty()) return "Branch not found.";
        if (productOpt.isEmpty()) return "Product not found.";

        Branch branch = branchOpt.get();
        Product product = productOpt.get();

        Optional<BranchInventory> inventoryOpt =
                branchInventoryRepository.findByBranchAndProduct(branch, product);

        if (inventoryOpt.isPresent()) {
            BranchInventory inventory = inventoryOpt.get();
            inventory.setQuantity(request.getQuantity()); // 👈 Overwrite quantity
            branchInventoryRepository.save(inventory);
            return "Inventory quantity updated.";
        } else {
            return "This product is not yet registered in the branch.";
        }
    }

    @DeleteMapping("/delete/{branchId}/{productId}")
    public String deleteProductFromBranchInventory(@PathVariable UUID branchId, @PathVariable UUID productId) {
        Optional<Branch> branchOpt = branchRepository.findById(branchId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (branchOpt.isEmpty()) return "Branch not found.";
        if (productOpt.isEmpty()) return "Product not found.";

        Optional<BranchInventory> inventoryOpt = branchInventoryRepository.findByBranchAndProduct(branchOpt.get(), productOpt.get());

        if (inventoryOpt.isPresent()) {
            branchInventoryRepository.delete(inventoryOpt.get());
            return "Product removed from branch inventory.";
        } else {
            return "Product not found in this branch's inventory.";
        }
    }

    // ✅ View all inventory for a branch
    @GetMapping("/branch/{branchId}")
    public List<BranchInventory> getInventoryByBranch(@PathVariable UUID branchId) {
        Optional<Branch> branchOpt = branchRepository.findById(branchId);
        if (branchOpt.isEmpty()) return Collections.emptyList();
        return branchInventoryRepository.findByBranch(branchOpt.get());
    }
}
