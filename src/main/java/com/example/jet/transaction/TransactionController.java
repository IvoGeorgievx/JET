package com.example.jet.transaction;

import com.example.jet.config.AuthenticatedUser;
import com.example.jet.transaction.dto.CreateTransactionDTO;
import com.example.jet.transaction.dto.OverallTransactionDTO;
import com.example.jet.transaction.dto.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("new")
    TransactionDTO createTransaction(@AuthenticationPrincipal AuthenticatedUser user, @RequestBody CreateTransactionDTO body) {
        return this.transactionService.createTransaction(user.getUserId(), body);
    }

    @GetMapping("overall")
    OverallTransactionDTO getOverallTransactions(@AuthenticationPrincipal AuthenticatedUser user, @RequestParam String period) {
        return this.transactionService.getOverallTransactions(user.getUserId(), period);
    }

    @PutMapping("{transactionId}")
    TransactionDTO updateTransaction(@AuthenticationPrincipal AuthenticatedUser user, @PathVariable String transactionId, @RequestBody CreateTransactionDTO body) {
        return this.transactionService.updateTransaction(user.getUserId(), body, UUID.fromString(transactionId));
    }


    @DeleteMapping("{transactionId}")
    ResponseEntity<Void> deleteTransaction(@PathVariable String transactionId) {
        this.transactionService.deleteTransaction(UUID.fromString(transactionId));
        return ResponseEntity.noContent().build();
    }

}
