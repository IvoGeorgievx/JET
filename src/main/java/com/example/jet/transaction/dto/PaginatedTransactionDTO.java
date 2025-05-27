package com.example.jet.transaction.dto;

import java.util.List;

public class PaginatedTransactionDTO {
    private List<TransactionDTO> transactions;
    private int totalPages;
    private int total;

    public PaginatedTransactionDTO(List<TransactionDTO> transactions, int totalPages, int total) {
        this.transactions = transactions;
        this.totalPages = totalPages;
        this.total = total;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
