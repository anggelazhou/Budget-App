package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for BudgetSystem class
class BudgetSystemTest {
    private BudgetSystem testBudgetSystem;

    @BeforeEach
    void runBefore() {
        testBudgetSystem = new BudgetSystem();
    }

    @Test
    void addCategory() {
        assertTrue(testBudgetSystem.getCategories().isEmpty());

        testBudgetSystem.addCategory(new Category("Food", 200.0));
        assertFalse(testBudgetSystem.getCategories().isEmpty());

        testBudgetSystem.addCategory(new Category("Clothes", 120.0));
        assertEquals(2, testBudgetSystem.getCategories().size());
    }

    @Test
    void removeCategory() {
        assertFalse(testBudgetSystem.removeCategory("Food"));

        testBudgetSystem.addCategory(new Category("Food", 200.0));
        testBudgetSystem.addCategory(new Category("Clothes", 120.0));
        assertNotNull(testBudgetSystem.selectCategory("Food"));
        assertTrue(testBudgetSystem.removeCategory("Food"));
        assertEquals(1, testBudgetSystem.getCategories().size());
        assertNull(testBudgetSystem.selectCategory("Food"));
        assertTrue(testBudgetSystem.removeCategory("Clothes"));
        assertTrue(testBudgetSystem.getCategories().isEmpty());
    }

    @Test
    void selectCategory() {
        assertNull(testBudgetSystem.selectCategory("Gas"));

        Category gasCategory = new Category("Gas", 100.0);
        Category clothesCategory = new Category("Clothes", 120.0);
        testBudgetSystem.addCategory(gasCategory);
        testBudgetSystem.addCategory(clothesCategory);
        assertEquals(gasCategory, testBudgetSystem.selectCategory("Gas"));
        assertEquals(clothesCategory, testBudgetSystem.selectCategory("Clothes"));
    }
}