package persistence;

import model.BudgetSystem;
import model.Category;
import model.Expense;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// modelled class after JsonReader class in sample app
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads budget system from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads budget system from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BudgetSystem read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudgetSystem(jsonObject);
    }

   // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses budget system from JSON object and returns it
    private BudgetSystem parseBudgetSystem(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        BudgetSystem budgetSystem = new BudgetSystem(name);
        addCategories(budgetSystem, jsonObject);
        return budgetSystem;
    }

    // MODIFIES: budgetSystem
    // EFFECTS: parses categories from JSON object and adds them to budget system
    private void addCategories(BudgetSystem budgetSystem, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("categories");
        for (Object json : jsonArray) {
            JSONObject nextCategory = (JSONObject) json;
            addCategory(budgetSystem, nextCategory);
        }
    }

    // MODIFIES: budgetSystem
    // EFFECTS: parses category from JSON object and adds it to budget system
    private void addCategory(BudgetSystem budgetSystem, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double budget = jsonObject.getDouble("budget");
        Category category = new Category(name, budget);
        budgetSystem.addCategory(category);
        addExpenses(category, jsonObject);
    }

    // MODIFIES: nextCategory
    // EFFECTS: parses expenses from JSON object and adds them to category
    private void addExpenses(Category nextCategory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(nextCategory, nextExpense);
        }
    }

    // MODIFIES: nextCategory
    // EFFECTS: parses category from JSON object and adds it to budget system
    private void addExpense(Category nextCategory, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        double amount = jsonObject.getDouble("amount");
        Expense expense = new Expense(description, amount);
        nextCategory.addExpense(expense);
    }
}
