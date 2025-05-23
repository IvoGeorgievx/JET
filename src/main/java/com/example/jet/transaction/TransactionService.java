package com.example.jet.transaction;

import com.example.jet.category.CategoryEntity;
import com.example.jet.category.CategoryRepository;
import com.example.jet.transaction.dto.CreateTransactionDTO;
import com.example.jet.transaction.dto.OverallTransactionDTO;
import com.example.jet.transaction.dto.TransactionDTO;
import com.example.jet.user.UserEntity;
import com.example.jet.user.UserRepository;
import com.example.jet.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, UserRepository userRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public TransactionDTO createTransaction(UUID userId, CreateTransactionDTO data) {
        Boolean isRecurring = false;
//        fix this ^
        CategoryEntity categoryEntity = this.categoryRepository.findById(data.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No such user found"));
        TransactionEntity transactionEntity = new TransactionEntity(data.getType(), data.getAmount(), data.getDescription(), categoryEntity, userEntity, isRecurring);
        TransactionEntity savedTransactionEntity = this.transactionRepository.save(transactionEntity);
        // make sure to set a recurring timeline later on.
        return new TransactionDTO(savedTransactionEntity.getId(), savedTransactionEntity.getType(), savedTransactionEntity.getAmount(), savedTransactionEntity.getDescription(), categoryEntity, savedTransactionEntity.getUserEntity().getId());
    }

    public OverallTransactionDTO getOverallTransactions(UUID userId, TransactionPeriod period) {
        UserEntity userEntity = this.userService.getUserById(userId);
        LocalDate now = LocalDate.now();
        LocalDate start = switch (period) {
            case DAILY -> now;
            case WEEKLY -> now.minusDays(6);
            case MONTHLY -> now.minusMonths(1);
            case YEARLY -> now.minusYears(1);
            default -> throw new IllegalArgumentException("Invalid period");
        };

        List<TransactionEntity> expenseTransactionEntities = this.transactionRepository.findByPeriod(userEntity.getId(), TransactionType.EXPENSE, now, start);
        List<TransactionEntity> incomeTransactionEntities = this.transactionRepository.findByPeriod(userEntity.getId(), TransactionType.INCOME, now, start);

        Float expensesAmount = expenseTransactionEntities.stream().map(TransactionEntity::getAmount).reduce(0f, Float::sum);
        Float incomeAmount = incomeTransactionEntities.stream().map(TransactionEntity::getAmount).reduce(0f, Float::sum);

        return new OverallTransactionDTO(incomeAmount, expensesAmount, period);
    }

}
