package com.example.jet.transaction;

import com.example.jet.category.CategoryEntity;
import com.example.jet.category.CategoryRepository;
import com.example.jet.category.dto.CategoryDTO;
import com.example.jet.transaction.dto.*;
import com.example.jet.user.UserEntity;
import com.example.jet.user.UserRepository;
import com.example.jet.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
        CategoryDTO categoryDto = new CategoryDTO(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getType(), categoryEntity.getBudget(), categoryEntity.getBudgetPeriod(), categoryEntity.getDefault());
        // make sure to set a recurring timeline later on.
        return new TransactionDTO(savedTransactionEntity.getId(), savedTransactionEntity.getDate(), savedTransactionEntity.getType(), savedTransactionEntity.getAmount(), savedTransactionEntity.getDescription(), categoryDto, savedTransactionEntity.getUserEntity().getId());
    }

    public TransactionDTO updateTransaction(CreateTransactionDTO data, UUID transactionId) {
        TransactionEntity transaction = this.transactionRepository.findById(transactionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No such transaction found"));

        transaction.setAmount(data.getAmount());
        transaction.setDescription(data.getDescription());
        transaction.setType(data.getType());
        TransactionEntity updatedTransaction = transactionRepository.save(transaction);
        CategoryDTO categoryDTO = convertToCategoryDTO(updatedTransaction);
//        returns nested data from the entities -> should fix the dto

        return new TransactionDTO(updatedTransaction.getId(), updatedTransaction.getDate(), updatedTransaction.getType(), updatedTransaction.getAmount(), updatedTransaction.getDescription(), categoryDTO, updatedTransaction.getUserEntity().getId());
    }

    public void deleteTransaction(UUID transactionId) {
        TransactionEntity transaction = this.transactionRepository.findById(transactionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No transaction with the given id found."));

        this.transactionRepository.delete(transaction);
    }

    public PaginatedTransactionDTO getTransactions(UUID userId, String period, int page, int perPage) {
        LocalDate end = LocalDate.now();
        LocalDate start = switch (period) {
            case "Daily" -> end;
            case "Weekly" -> end.minusDays(6);
            case "Monthly" -> end.minusMonths(1);
            case "Yearly" -> end.minusYears(1);
            default -> throw new IllegalArgumentException("Invalid period");
        };

        PageRequest pageRequest = PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, "date"));
        Page<TransactionEntity> transactions = this.transactionRepository.findByUserEntity(userId, pageRequest, start, end);
        List<TransactionDTO> transactionDTOS = transactions.stream().map(transaction -> new TransactionDTO(transaction.getId(), transaction.getDate(), transaction.getType(), transaction.getAmount(), transaction.getDescription(), convertToCategoryDTO(transaction), transaction.getUserEntity().getId())).toList();

        return new PaginatedTransactionDTO(transactionDTOS, transactions.getTotalPages(), (int) transactions.getTotalElements());

    }

    public OverallTransactionDTO getOverallTransactions(UUID userId, String period) {
        UserEntity userEntity = this.userService.getUserById(userId);
        AbstractMap.SimpleEntry<LocalDate, LocalDate> time = getStartAndEndPeriod(period);
        LocalDate start = time.getKey();
        LocalDate end = time.getValue();


        List<TransactionEntity> expenseTransactionEntities = this.transactionRepository.findByPeriod(userEntity.getId(), TransactionType.EXPENSE, start, end);
        List<TransactionEntity> incomeTransactionEntities = this.transactionRepository.findByPeriod(userEntity.getId(), TransactionType.INCOME, start, end);

        Float expensesAmount = expenseTransactionEntities.stream().map(TransactionEntity::getAmount).reduce(0f, Float::sum);
        Float incomeAmount = incomeTransactionEntities.stream().map(TransactionEntity::getAmount).reduce(0f, Float::sum);

        return new OverallTransactionDTO(incomeAmount, expensesAmount, period);
    }

    private CategoryDTO convertToCategoryDTO(TransactionEntity transaction) {
        return new CategoryDTO(transaction.getCategoryEntity().getId(), transaction.getCategoryEntity().getName(), transaction.getCategoryEntity().getType(), transaction.getCategoryEntity().getBudget(), transaction.getCategoryEntity().getBudgetPeriod(), transaction.getCategoryEntity().getDefault());
    }

    public List<SpendingByCategoryDTO> getSpendingByCategory(UUID userId, String period) {
        AbstractMap.SimpleEntry<LocalDate, LocalDate> time = getStartAndEndPeriod(period);
        LocalDate start = time.getKey();
        LocalDate end = time.getValue();

        List<TransactionEntity> entities = this.transactionRepository.findRawByUserEntity(userId, start, end);

        Map<String, List<TransactionEntity>> grouped = entities.stream()
                .collect(Collectors.groupingBy(e -> e.getCategoryEntity().getName()));

        return grouped.entrySet().stream()
                .map(entry -> {
                    String categoryName = entry.getKey();
                    int expense = entry.getValue().stream()
                            .filter(t -> t.getType() == TransactionType.EXPENSE)
                            .mapToInt(t -> t.getAmount().intValue())
                            .sum();
                    int income = entry.getValue().stream()
                            .filter(t -> t.getType() == TransactionType.INCOME)
                            .mapToInt(t -> t.getAmount().intValue())
                            .sum();
                    return new SpendingByCategoryDTO(categoryName, expense, income);
                })
                .collect(Collectors.toList());
    }

    private AbstractMap.SimpleEntry<LocalDate, LocalDate> getStartAndEndPeriod(String period) {
        LocalDate end = LocalDate.now();
        LocalDate startPeriod = switch (period) {
            case "Daily" -> end;
            case "Weekly" -> end.minusDays(6);
            case "Monthly" -> end.minusMonths(1);
            case "Yearly" -> end.minusYears(1);
            default -> throw new IllegalArgumentException("Invalid period");
        };
        return new AbstractMap.SimpleEntry<>(startPeriod, end);
    }

}
