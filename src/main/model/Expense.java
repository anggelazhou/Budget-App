package model;

public class Expense {
    private final String description;
    private final double amount;

    // REQUIRES: amount >= 0
    // EFFECTS: constructs an expense with given description and amount
    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // EFFECTS: returns description of expense
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns expense amount
    public double getAmount() {
        return amount;
    }
}
