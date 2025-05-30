package com.example.jet.transaction;

public enum TransactionType {
    INCOME("Income"),
    EXPENSE("Expense");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}