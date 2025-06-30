package com.example.jet.transaction.dto;

public class SpendingByCategoryDTO {

    private String categoryName;
    private int expense;
    private int income;

    public SpendingByCategoryDTO(String categoryName, int expense, int income) {
        this.categoryName = categoryName;
        this.expense = expense;
        this.income = income;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
