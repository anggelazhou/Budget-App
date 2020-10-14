package ui;

import model.BudgetSystem;
import model.Category;
import model.Expense;

import java.util.Scanner;

public class Main {
    private static final int BUDGET_SYSTEM_CHOICE_EXIT = 0;
    private static final int BUDGET_SYSTEM_CHOICE_SELECT = 1;
    private static final int BUDGET_SYSTEM_CHOICE_ADD = 2;
    private static final int BUDGET_SYSTEM_CHOICE_REMOVE = 3;

    public static void main(String[] args) {
        BudgetSystem myBudgets = new BudgetSystem();
        int choiceFromBudgetSystem = budgetSystemChoice(myBudgets);
        while (choiceFromBudgetSystem != BUDGET_SYSTEM_CHOICE_EXIT) {
            if (choiceFromBudgetSystem == BUDGET_SYSTEM_CHOICE_SELECT) {
                selectCategoryChoice(myBudgets);
            } else if (choiceFromBudgetSystem == BUDGET_SYSTEM_CHOICE_ADD) {
                addCategory(myBudgets);
            } else if (choiceFromBudgetSystem == BUDGET_SYSTEM_CHOICE_REMOVE) {
                removeCategory(myBudgets);
            }
            System.out.println();
            choiceFromBudgetSystem = budgetSystemChoice(myBudgets);
        }

    }

    private static void removeCategory(BudgetSystem myBudgets) {
        System.out.println("-----------------------------");
        System.out.print("Please enter category name (b = go back): ");
        Scanner input = new Scanner(System.in);
        String categoryName = input.nextLine();

        if (categoryName.equals("b")) {
            return;
        }

        myBudgets.removeCategory(categoryName);

    }

    private static void addCategory(BudgetSystem myBudgets) {
        System.out.println("-----------------------------");
        System.out.print("Please enter category name (b = go back): ");
        Scanner input = new Scanner(System.in);
        String categoryName = input.nextLine();

        if (categoryName.equals("b")) {
            return;
        }

        System.out.print("Please enter category budget: ");
        double budget = input.nextDouble();

        Category category = new Category(categoryName, budget);

        myBudgets.addCategory(category);

    }

    private static int budgetSystemChoice(BudgetSystem budgetSystem) {
        displayBudgetSystemContent(budgetSystem);

        displayChoices("1 = Select Category", "2 = Add Category", "3 = Remove Category");

        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();

        while (userChoice > 3 || userChoice < 0) {
            System.out.print("Error. Please re-enter: ");
            userChoice = input.nextInt();
        }
        return userChoice;
    }

    private static void displayBudgetSystemContent(BudgetSystem budgetSystem) {
        System.out.println("Categories: ");
        System.out.println();

        if (budgetSystem.getCategories().isEmpty()) {
            System.out.println("No categories yet!");
            System.out.println();
        } else {
            for (Category c : budgetSystem.getCategories()) {
                System.out.println("Name: " + c.getName());
                System.out.println("Balance: " + c.calculateBalance());
                System.out.println();
            }
        }
    }

    private static void displayChoices(String s, String s2, String s3) {
        System.out.println("-----------------------------");
        System.out.println("Choices: ");
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("0 = Exit");
        System.out.println();
        System.out.print("Enter choice: ");
    }

    private static void selectCategoryChoice(BudgetSystem budgetSystem) {

        System.out.println("-----------------------------");
        System.out.print("Please enter category name (b = go back): ");
        Scanner input = new Scanner(System.in);
        String categoryName = input.nextLine();

        if (categoryName.equals("b")) {
            return;
        }

        Category chosenCategory = budgetSystem.selectCategory(categoryName);

        //when the category the user inputs does not exist...
        while (chosenCategory == null) {
            System.out.print("Invalid category name. Please re-enter (b = go back): ");
            categoryName = input.nextLine();

            if (categoryName.equals("b")) {
                return;
            }

            chosenCategory = budgetSystem.selectCategory(categoryName);
        }

        // extracted a method to shorten
        maintainCategory(chosenCategory);

    }

    private static void modifyBudget(Category chosenCategory) {
        System.out.println("-----------------------------");
        System.out.print("Please enter new budget: ");

        Scanner input = new Scanner(System.in);
        double budget = input.nextDouble();

        chosenCategory.setBudget(budget);
    }

    private static void addExpense(Category chosenCategory) {
        System.out.println("-----------------------------");
        System.out.print("Please enter expense description: ");
        Scanner input = new Scanner(System.in);
        String description = input.nextLine();

        System.out.print("Please enter expense amount: ");
        double amount = input.nextDouble();

        Expense expense = new Expense(description, amount);
        chosenCategory.addExpense(expense);
    }

    private static void resetBalance(Category chosenCategory) {
        chosenCategory.resetBalance();
    }

    private static void maintainCategory(Category chosenCategory) {
        displayCategoryContent(chosenCategory);
        displayChoices("1 = Modify Budget", "2 = Add Expense", "3 = Reset Balance");

        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();

        while (userChoice > 3 || userChoice < 0) {
            System.out.print("Error. Please re-enter: ");
            userChoice = input.nextInt();
        }

        if (userChoice != 0) {
            if (userChoice == 1) {
                modifyBudget(chosenCategory);
            } else if (userChoice == 2) {
                addExpense(chosenCategory);
            } else {
                resetBalance(chosenCategory);
            }

            maintainCategory(chosenCategory);
        }
    }

    private static void displayCategoryContent(Category chosenCategory) {
        if (chosenCategory.getExpenses().isEmpty()) {
            System.out.println("No expenses yet!");
            System.out.println();
        } else {
            for (Expense e : chosenCategory.getExpenses()) {
                System.out.println("Description: " + e.getDescription());
                System.out.println("Amount: " + e.getAmount());
                System.out.println();
            }
        }
        System.out.println("Balance: " + chosenCategory.calculateBalance());
    }
}
