package com.example.jet.transaction;

import com.example.jet.category.CategoryEntity;
import com.example.jet.category.CategoryRepository;
import com.example.jet.transaction.dto.CreateTransactionDTO;
import com.example.jet.transaction.dto.OverallTransactionDTO;
import com.example.jet.transaction.dto.TransactionDTO;
import com.example.jet.user.UserEntity;
import com.example.jet.user.UserRepository;
import com.example.jet.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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

    public TransactionDTO updateTransaction(UUID userId, CreateTransactionDTO data, UUID transactionId) {
        TransactionEntity transaction = this.transactionRepository.findById(transactionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No such transaction found"));

        transaction.setAmount(data.getAmount());
        transaction.setDescription(data.getDescription());
        transaction.setType(data.getType());
        TransactionEntity updatedTransaction = transactionRepository.save(transaction);

//        returns nested data from the entities -> should fix the dto

        return new TransactionDTO(updatedTransaction.getId(), updatedTransaction.getType(), updatedTransaction.getAmount(), updatedTransaction.getDescription(), updatedTransaction.getCategoryEntity(), updatedTransaction.getUserEntity().getId());
    }

    public void deleteTransaction(UUID transactionId) {
        TransactionEntity transaction = this.transactionRepository.findById(transactionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No transaction with the given id found."));

        this.transactionRepository.delete(transaction);
    }

    public OverallTransactionDTO getOverallTransactions(UUID userId, String period) {
        UserEntity userEntity = this.userService.getUserById(userId);
        LocalDate end = LocalDate.now();
        LocalDate start = switch (period) {
            case "Daily" -> end;
            case "Weekly" -> end.minusDays(6);
            case "Monthly" -> end.minusMonths(1);
            case "Yearly" -> end.minusYears(1);
            default -> throw new IllegalArgumentException("Invalid period");
        };


        List<TransactionEntity> expenseTransactionEntities = this.transactionRepository.findByPeriod(userEntity.getId(), TransactionType.EXPENSE, start, end);
        List<TransactionEntity> incomeTransactionEntities = this.transactionRepository.findByPeriod(userEntity.getId(), TransactionType.INCOME, start, end);

        Float expensesAmount = expenseTransactionEntities.stream().map(TransactionEntity::getAmount).reduce(0f, Float::sum);
        Float incomeAmount = incomeTransactionEntities.stream().map(TransactionEntity::getAmount).reduce(0f, Float::sum);

        return new OverallTransactionDTO(incomeAmount, expensesAmount, period);
    }

}
