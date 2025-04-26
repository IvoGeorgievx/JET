package com.example.jet.category;

import com.example.jet.transaction.Transaction;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity()
@Table(name = "jet_category")
public class Category {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CategoryType type;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
