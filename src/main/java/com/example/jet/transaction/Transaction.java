package com.example.jet.transaction;

import com.example.jet.category.Category;
import jakarta.persistence.*;

import java.util.UUID;

@Entity()
@Table(name = "jet_transaction")
public class Transaction {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType type;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
