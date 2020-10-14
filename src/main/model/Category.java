package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private double budget;
    private final List<Expense> expenses;

    // REQUIRES: budget >= 0, name is not of 0 length
    // EFFECTS: constructs a category with a given name and budget
    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.expenses = new ArrayList<>();
    }

    // EFFECTS: returns name of category
    public String getName() {
        return name;
    }

    // EFFECTS: returns budget of category
    public double getBudget() {
        return budget;
    }

    // EFFECTS: sets budget of category
    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    /*
     * REQUIRES: expense >= 0
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
     * EFFECTS: clears previous expenses within a category, which resets balance to original budget
     */
    public void resetBalance() {
        expenses.clear();
    }

}
