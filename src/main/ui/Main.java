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
        System.out.print("Please enter category name: (b = go back)");
        Scanner input = new Scanner(System.in);
        String categoryName = input.nextLine();

        if (categoryName.equals("b")) {
            return;
        }

        myBudgets.removeCategory(categoryName);

    }

    private static void addCategory(BudgetSystem myBudgets) {
        System.out.print("Please enter category name: (b = go back)");
        Scanner input = new Scanner(System.in);
        String categoryName = input.nextLine();

        if (categoryName.equals("b")) {
            return;
        }

        System.out.println("Please enter category budget: ");
        double budget = input.nextDouble();

        Category category = new Category(categoryName, budget);

        myBudgets.addCategory(category);

    }

    private static int budgetSystemChoice(BudgetSystem budgetSystem) {
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

        System.out.println("Choices: ");
        System.out.println("-----------------------------");
        System.out.println("1 = Select Category");
        System.out.println("2 = Add Category");
        System.out.println("3 = Remove Category");
        System.out.println("0 = Exit");
        System.out.println();
        System.out.print("Enter choice: ");

        Scanner input = new Scanner (System.in);
        int userChoice = input.nextInt();

        while (userChoice > 3 || userChoice < 0) {
            System.out.print("Error. Please re-enter: ");
            userChoice = input.nextInt();
        }
        return userChoice;
    }

    private static void selectCategoryChoice (BudgetSystem budgetSystem) {

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
        int choice = maintainCategory(chosenCategory);

        while (choice != 0) {
            if (choice == 1) {
                modifyBudget(chosenCategory);
            } else if (choice == 2) {
                addExpense(chosenCategory);
            } else if (choice == 3) {
                resetBalance(chosenCategory);
            }
            choice = maintainCategory(chosenCategory);
        }

    }

    private static void modifyBudget(Category chosenCategory) {
        System.out.println("Please enter new budget: ");

        Scanner input = new Scanner(System.in);
        double budget = input.nextDouble();

        chosenCategory.setBudget(budget);
    }

    private static void addExpense(Category chosenCategory) {
        System.out.println("Please enter expense description: ");
        Scanner input = new Scanner(System.in);
        String description = input.nextLine();

        System.out.println("Please enter expense amount: ");
        double amount = input.nextDouble();

        Expense expense = new Expense(description, amount);
        chosenCategory.addExpense(expense);
    }

    private static void resetBalance(Category chosenCategory) {
        chosenCategory.resetBalance();
    }

    private static int maintainCategory(Category chosenCategory) {
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
        System.out.println("-----------------------------");
        System.out.println("Choices: ");
        System.out.println("1 = Modify Budget");
        System.out.println("2 = Add Expense");
        System.out.println("3 = Reset Balance");
        System.out.println("0 = Exit");
        System.out.println();
        System.out.print("Enter choice: ");

        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();

        while (userChoice > 3 || userChoice < 0) {
            System.out.print("Error. Please re-enter: ");
            userChoice = input.nextInt();
        }
        return userChoice;
    }
}
