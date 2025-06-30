package com.example.jet.transaction;

import com.example.jet.category.CategoryService;
import com.example.jet.config.AuthenticatedUser;
import com.example.jet.transaction.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService, CategoryService categoryService) {
        this.transactionService = transactionService;
    }

    @PostMapping("new")
    TransactionDTO createTransaction(@AuthenticationPrincipal AuthenticatedUser user, @RequestBody CreateTransactionDTO body) {
        return this.transactionService.createTransaction(user.getUserId(), body);
    }

    @GetMapping
    PaginatedTransactionDTO getTransactions(@AuthenticationPrincipal AuthenticatedUser user, @RequestParam("period") String period, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "perPage", defaultValue = "10") int perPage) {
        return this.transactionService.getTransactions(user.getUserId(), period, page, perPage);
    }


    @PutMapping("{transactionId}")
    TransactionDTO updateTransaction(@PathVariable String transactionId, @RequestBody CreateTransactionDTO body) {
        return this.transactionService.updateTransaction(body, UUID.fromString(transactionId));
    }

    @GetMapping("overall")
    OverallTransactionDTO getOverallTransactions(@AuthenticationPrincipal AuthenticatedUser user, @RequestParam String period) {
        return this.transactionService.getOverallTransactions(user.getUserId(), period);
    }


    @DeleteMapping("{transactionId}")
    ResponseEntity<Void> deleteTransaction(@PathVariable String transactionId) {
        this.transactionService.deleteTransaction(UUID.fromString(transactionId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("spending")
    List<SpendingByCategoryDTO> getSpendingByCategory(@AuthenticationPrincipal AuthenticatedUser user, @RequestParam(value = "period", defaultValue = "YEARLY") String period) {
        return this.transactionService.getSpendingByCategory(user.getUserId(), period);
    }

}
