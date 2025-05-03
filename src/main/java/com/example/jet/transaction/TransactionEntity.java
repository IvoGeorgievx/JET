package com.example.jet.transaction;

import com.example.jet.category.CategoryEntity;
import com.example.jet.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity()
@Table(name = "jet_transaction")
public class TransactionEntity {
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

    @Column(name = "created_at")
    private LocalDate date;

    @Column(name = "recurring")
    private boolean recurring;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;


    public TransactionEntity(TransactionType type, Float amount, String description, CategoryEntity categoryEntity, UserEntity userEntity, boolean recurring) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
        this.categoryEntity = categoryEntity;
        this.userEntity = userEntity;
        this.recurring = recurring;
    }

    public TransactionEntity(TransactionType type, Float amount, String description, CategoryEntity categoryEntity, UserEntity userEntity) {
        this(type, amount, description, categoryEntity, userEntity, false);
    }


    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return categoryEntity;
    }

    public void setCategory(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
