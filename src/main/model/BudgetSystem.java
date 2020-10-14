package model;


import java.util.ArrayList;
import java.util.List;

public class BudgetSystem {
    private final List<Category> categories;

    // EFFECTS: constructs a budget system with no categories
    public BudgetSystem() {
        this.categories = new ArrayList<>();
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
}
