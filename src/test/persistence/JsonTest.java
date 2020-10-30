package persistence;

import model.BudgetSystem;
import model.Category;
import model.Expense;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected BudgetSystem budgetSystem;

    protected Category food;
    protected Expense burger;
    protected Expense drink;

    protected Category car;
    protected Expense gas;

    @BeforeEach
    public void setup() {
        budgetSystem = new BudgetSystem("Angela's BS");
        food = new Category("food", 100);
        burger = new Expense("burger", 6);
        drink = new Expense("drink", 5);
        food.addExpense(burger);
        food.addExpense(drink);

        car = new Category("car", 150);
        gas = new Expense("gas", 60);
        car.addExpense(gas);

        budgetSystem.addCategory(food);
        budgetSystem.addCategory(car);
    }

    protected void checkCategories(List<Category> categories, List<Category> actualCategories) {
        assertEquals(categories.size(), actualCategories.size());
        for (int i = 0; i < categories.size(); i++) {
            checkCategory(categories.get(i), actualCategories.get(i));
        }
    }

    protected void checkCategory(Category category, Category actualCategory) {
        assertEquals(category.getName(), actualCategory.getName());
        assertEquals(category.getBudget(), actualCategory.getBudget());
        checkExpenses(category.getExpenses(), actualCategory.getExpenses());
    }

    protected void checkExpenses(List<Expense> expenses, List<Expense> actualExpenses) {
        assertEquals(expenses.size(), actualExpenses.size());
        for (int i = 0; i < expenses.size(); i++) {
            checkExpense(expenses.get(i), actualExpenses.get(i));
        }
    }

    protected void checkExpense(Expense expense, Expense actualExpense) {
        assertEquals(expense.getDescription(), actualExpense.getDescription());
        assertEquals(expense.getAmount(), actualExpense.getAmount());
    }

}
