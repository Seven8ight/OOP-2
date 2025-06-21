package com.test.oop2.repository;

import com.test.oop2.model.BranchInventory;
import com.test.oop2.model.Branch;
import com.test.oop2.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchInventoryRepo extends JpaRepository<BranchInventory, UUID> {
    List<BranchInventory> findByBranch(Branch branch);
    Optional<BranchInventory> findByBranchAndProduct(Branch branch, Product product);
}
