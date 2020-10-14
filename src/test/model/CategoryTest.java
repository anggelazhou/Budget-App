package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private Category testCategory;


    @BeforeEach
    void runBefore() {
        testCategory = new Category("Food", 200.0);
    }

    @Test
    void testAddExpense() {
        assertTrue(testCategory.getExpenses().isEmpty());

        testCategory.addExpense(new Expense("Pizza", 12.0));
        assertFalse(testCategory.getExpenses().isEmpty());

        testCategory.addExpense(new Expense("Coke", 3.0));
        assertEquals(2, testCategory.getExpenses().size());
    }

    @Test
    void testCalculateBalanceWithinBudget() {
        assertEquals(200, testCategory.calculateBalance());

        testCategory.addExpense(new Expense("Pizza", 12.0));
        assertEquals(188, testCategory.calculateBalance());

        testCategory.addExpense(new Expense("Apples", 5.0));
        assertEquals(183, testCategory.calculateBalance());

    }

    @Test
    void testCalculateBalanceOverBudget() {
        assertEquals(200, testCategory.calculateBalance());

        testCategory.addExpense(new Expense("Wine", 198.0));
        assertEquals(2, testCategory.calculateBalance());

        testCategory.addExpense(new Expense("Dozen cupcake", 30.0));
        assertEquals(-28, testCategory.calculateBalance());
    }

    @Test
    void testCalculateBalanceChangedBudget() {
        assertEquals(200, testCategory.calculateBalance());

        testCategory.addExpense(new Expense("Wine", 198.0));
        assertEquals(2, testCategory.calculateBalance());

        testCategory.setBudget(250);
        testCategory.addExpense(new Expense("Lettuce", 5.0));
        assertEquals(47, testCategory.calculateBalance());

        testCategory.setBudget(100);
        assertEquals(-103, testCategory.calculateBalance());
    }


    @Test
    void testResetBalanceBalancePositive() {
        //no expenses
        testCategory.resetBalance();
        assertEquals(200, testCategory.calculateBalance());

        //one expense close to budget, but not over
        testCategory.addExpense(new Expense("Wine", 198.0));
        assertEquals(2, testCategory.calculateBalance());
        testCategory.resetBalance();
        assertEquals(200, testCategory.calculateBalance());

        //change budget, more than one expense but total not even close to budget
        testCategory.setBudget(250);
        testCategory.addExpense(new Expense("Lettuce", 5.0));
        testCategory.addExpense(new Expense("Smoothie", 7.0));
        testCategory.resetBalance();
        assertEquals(250, testCategory.calculateBalance());
    }

    @Test
    void testResetBalanceWithNegativeBalance() {
        //one expense, expenses just over budget
        testCategory.addExpense(new Expense("Champagne", 201.0));
        assertEquals(-1, testCategory.calculateBalance());
        testCategory.resetBalance();
        assertEquals(200, testCategory.calculateBalance());

        //change budget, more than one expense, expenses way over budget
        testCategory.setBudget(15);
        testCategory.addExpense(new Expense("Korean Grapes", 27.0));
        testCategory.addExpense(new Expense("Gummy Bears", 2.0));
        assertEquals(-14, testCategory.calculateBalance());
        testCategory.resetBalance();
        assertEquals(15, testCategory.calculateBalance());
        assertEquals(15, testCategory.getBudget());

    }
}