package com.example.jet.transaction;

import com.example.jet.category.Category;
import com.example.jet.category.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    TransactionDTO createTransaction(CreateTransactionDTO data) {
        Category category = this.categoryRepository.findById(data.getCategory().getId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        System.out.println(category);
        Transaction transaction = new Transaction(data.getType(), data.getAmount(), data.getDescription(), category);
        Transaction savedTransaction = this.transactionRepository.save(transaction);
        return new TransactionDTO(savedTransaction.getId(), savedTransaction.getType(), savedTransaction.getAmount(), savedTransaction.getDescription(), savedTransaction.getCategory());
    }
}
