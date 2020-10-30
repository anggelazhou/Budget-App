package model;

import org.json.JSONObject;

// Represents an expense (within a class) having a description and amount
public class Expense {
    private final String description;
    private final double amount;

    // REQUIRES: amount >= 0, description is not of 0 length
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

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }
}
