package com.example.jet.transaction;

import com.example.jet.transaction.dto.CreateTransactionDTO;
import com.example.jet.transaction.dto.OverallTransactionDTO;
import com.example.jet.transaction.dto.TransactionDTO;
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
    TransactionDTO createTransaction(@RequestBody CreateTransactionDTO body) {
        return this.transactionService.createTransaction(body);
    }

    @GetMapping("overall")
    OverallTransactionDTO getOverallTransactions() {
        return this.transactionService.getOverallTransactions(UUID.fromString("f98a9b98-fed8-459f-af8f-4ef3bf376134"), TransactionPeriod.DAILY);
    }
}
