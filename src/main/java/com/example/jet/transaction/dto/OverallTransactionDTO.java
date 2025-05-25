package com.example.jet.transaction.dto;

public class OverallTransactionDTO {
    private Float income;
    private Float expense;
    private String period;

    public OverallTransactionDTO(Float income, Float expense, String period) {
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
