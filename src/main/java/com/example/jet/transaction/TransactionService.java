package com.example.jet.transaction;

import com.example.jet.category.Category;
import com.example.jet.category.CategoryRepository;
import com.example.jet.transaction.dto.CreateTransactionDTO;
import com.example.jet.transaction.dto.OverallTransactionDTO;
import com.example.jet.transaction.dto.TransactionDTO;
import com.example.jet.user.User;
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


    public TransactionDTO createTransaction(CreateTransactionDTO data) {
        Boolean isRecurring = data.isRecurring();
        Category category = this.categoryRepository.findById(data.getCategory().getId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        User user = this.userRepository.findById(data.getUserId()).orElseThrow(() -> new IllegalArgumentException("No such user found"));
        Transaction transaction = new Transaction(data.getType(), data.getAmount(), data.getDescription(), data.getCategory(), user, isRecurring);
        Transaction savedTransaction = this.transactionRepository.save(transaction);
        // make sure to set a recurring timeline later on.
        return new TransactionDTO(savedTransaction.getId(), savedTransaction.getType(), savedTransaction.getAmount(), savedTransaction.getDescription(), category, savedTransaction.getUser().getId());
    }

    public OverallTransactionDTO getOverallTransactions(UUID userId, TransactionPeriod period) {
        User user = this.userService.getUserById(userId);
        LocalDate now = LocalDate.now();
        LocalDate start = switch (period) {
            case DAILY -> now;
            case WEEKLY -> now.minusDays(6);
            case MONTHLY -> now.minusMonths(1);
            case YEARLY -> now.minusYears(1);
            default -> throw new IllegalArgumentException("Invalid period");
        };

        List<Transaction> expenseTransactions = this.transactionRepository.findByPeriod(user.getId(), TransactionType.EXPENSE, now, start);
        List<Transaction> incomeTransactions = this.transactionRepository.findByPeriod(user.getId(), TransactionType.INCOME, now, start);

        Float expensesAmount = expenseTransactions.stream().map(Transaction::getAmount).reduce(0f, Float::sum);
        Float incomeAmount = incomeTransactions.stream().map(Transaction::getAmount).reduce(0f, Float::sum);

        return new OverallTransactionDTO(incomeAmount, expensesAmount, period);
    }

}
