package com.example.jet.transaction;

import com.example.jet.category.Category;
import com.example.jet.category.CategoryRepository;
import com.example.jet.user.User;
import com.example.jet.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


    TransactionDTO createTransaction(CreateTransactionDTO data) {
        Category category = this.categoryRepository.findById(data.getCategory().getId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        User user = this.userRepository.findById(data.getUserId()).orElseThrow(() -> new IllegalArgumentException("No such user found"));
        Transaction transaction = new Transaction(data.getType(), data.getAmount(), data.getDescription(), data.getCategory(), user);
        Transaction savedTransaction = this.transactionRepository.save(transaction);
        return new TransactionDTO(savedTransaction.getId(), savedTransaction.getType(), savedTransaction.getAmount(), savedTransaction.getDescription(), category, savedTransaction.getUser().getId());
    }
}
