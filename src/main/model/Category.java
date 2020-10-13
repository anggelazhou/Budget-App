package model;

import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private final int year;
    private final int month;
    private double budget;
    private final List<Expense> expenses;

    // for history of budgets
    public Category(String name, int year, int month, double budget) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.budget = budget;
        this.expenses = new ArrayList<>();
    }

    // for current budgets
    public Category(String name, double budget) {
        this(name, LocalDate.now().getYear(), LocalDate.now().getMonthValue(), budget);
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS:
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }


}
