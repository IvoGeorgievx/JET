package com.example.jet.transaction.dto;

import com.example.jet.transaction.TransactionPeriod;

public class OverallTransactionDTO {
    private Float income;
    private Float expense;
    private TransactionPeriod period;

    public OverallTransactionDTO(Float income, Float expense, TransactionPeriod period) {
        this.income = income;
        this.expense = expense;
        this.period = period;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Float getExpense() {
        return expense;
    }

    public void setExpense(Float expense) {
        this.expense = expense;
    }

    public TransactionPeriod getPeriod() {
        return period;
    }

    public void setPeriod(TransactionPeriod period) {
        this.period = period;
    }
}
