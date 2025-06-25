package com.test.oop2.repository;

import com.test.oop2.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepo extends JpaRepository<Branch, UUID> {
}
