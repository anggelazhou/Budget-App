package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for BudgetSystem class
class BudgetSystemTest {
    private BudgetSystem testBudgetSystem;

    @BeforeEach
    void runBefore() {
        testBudgetSystem = new BudgetSystem("Budget System 1");
    }

    @Test
    void testAddCategory() {
        assertEquals("Budget System 1", testBudgetSystem.getName());
        testBudgetSystem.setName("BS1");
        assertEquals("BS1", testBudgetSystem.getName());
        assertTrue(testBudgetSystem.getCategories().isEmpty());

        testBudgetSystem.addCategory(new Category("Food", 200.0));
        assertFalse(testBudgetSystem.getCategories().isEmpty());

        testBudgetSystem.addCategory(new Category("Clothes", 120.0));
        assertEquals(2, testBudgetSystem.getCategories().size());
    }

    @Test
    void testRemoveCategoryWhenBudgetSystemEmpty() {
        assertFalse(testBudgetSystem.removeCategory("Food"));
    }

    @Test
    void testRemoveCategoryWhenBudgetSystemNotEmpty() {
        testBudgetSystem.addCategory(new Category("Food", 200.0));
        testBudgetSystem.addCategory(new Category("Clothes", 120.0));
        assertNotNull(testBudgetSystem.selectCategory("Food"));
        assertTrue(testBudgetSystem.removeCategory("Food"));
        assertEquals(1, testBudgetSystem.getCategories().size());
        assertNull(testBudgetSystem.selectCategory("Food"));
        assertFalse(testBudgetSystem.removeCategory("Gas"));
        assertTrue(testBudgetSystem.removeCategory("Clothes"));
        assertTrue(testBudgetSystem.getCategories().isEmpty());
    }

    @Test
    void testSelectCategoryWhenBudgetSystemEmpty() {
        assertNull(testBudgetSystem.selectCategory("Gas"));
    }

    @Test
    void testSelectCategoryWhenBudgetSystemNotEmpty() {
        Category gasCategory = new Category("Gas", 100.0);
        Category clothesCategory = new Category("Clothes", 120.0);
        testBudgetSystem.addCategory(gasCategory);
        testBudgetSystem.addCategory(clothesCategory);
        assertEquals(gasCategory, testBudgetSystem.selectCategory("Gas"));
        assertEquals(clothesCategory, testBudgetSystem.selectCategory("Clothes"));
    }
}