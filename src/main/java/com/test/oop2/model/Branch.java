package com.test.oop2.model;

import jakarta.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BranchInventory> inventories = new ArrayList<>();

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<BranchInventory> getInventories() { return inventories; }
    public void setInventories(List<BranchInventory> inventories) { this.inventories = inventories; }
}
