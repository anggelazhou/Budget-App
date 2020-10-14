package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private double budget;
    private final List<Expense> expenses;

    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.expenses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    /*
     * REQUIRES: the expense is a positive value (i.e. no negative prices)
     * MODIFIES: this
     * EFFECTS: adds an expense to a category
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /*
     * MODIFIES: this
     * EFFECTS: calculates amount you have left to spend in certain category given a budget and expenses;
     * budget is negative if expenses exceeds budget
     */
    public double calculateBalance() {
        double balance = budget;
        for (Expense e : expenses) {
            balance -= e.getAmount();
        }
        return balance;
    }

    /*
     * MODIFIES: this
     * EFFECTS: clears previous expenses within a category, which also resets balance to original budget
     */
    public void resetBalance() {
        expenses.clear();
    }

}
