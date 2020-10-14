package model;


import java.util.ArrayList;
import java.util.List;

public class BudgetSystem {
    private final List<Category> categories;

    public BudgetSystem() {
        this.categories = new ArrayList<>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    /*
     * REQUIRES: category must not already exist
     * MODIFIES: this
     * EFFECTS:
     */
    public void addCategory(Category category) {
        categories.add(category);
    }

    /*
     * MODIFIES: this
     * EFFECTS: If there exists a category with provided
     *  name, removes category from list returns true. Otherwise, returns false
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
     * EFFECTS: returns category with given name if found. Otherwise, returns null.
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
