package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a budget system having a collection of categories
public class BudgetSystem implements Writable {
    private String name;
    private final List<Category> categories;

    // EFFECTS: constructs a budget system with no categories
    public BudgetSystem(String name) {
        this.name = name;
        this.categories = new ArrayList<>();
    }

    // EFFECTS: returns name of budget system
    public String getName() {
        return name;
    }

    //EFFECTS: sets name of budget system
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns categories in budget system
    public List<Category> getCategories() {
        return categories;
    }

    /*
     * REQUIRES: category must not already exist
     * MODIFIES: this
     * EFFECTS: adds a new category to the budget system
     */
    public void addCategory(Category category) {
        categories.add(category);
    }

    /*
     * MODIFIES: this
     * EFFECTS: If there exists a category in the budget system with the provided name,
     * removes category from the list and returns true. Otherwise, returns false.
     */
    public boolean removeCategory(String name) {
        for (Category c : categories) {
            if (c.getName().equals(name)) {
                categories.remove(c);
                return true;
            }

        }

        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Returns category with given name if found. Otherwise, returns null.
     */
    public Category selectCategory(String name) {
        for (Category c : categories) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("categories", categoriesToJson());
        return json;
    }

    // EFFECTS: returns categories in this budget system as a JSON array
    private JSONArray categoriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Category c : categories) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
