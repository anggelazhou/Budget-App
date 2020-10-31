package ui;

import model.BudgetSystem;
import model.Category;
import model.Expense;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int BUDGET_SYSTEM_CHOICE_EXIT = 0;
    private static final int BUDGET_SYSTEM_CHOICE_SELECT = 1;
    private static final int BUDGET_SYSTEM_CHOICE_ADD = 2;
    private static final int BUDGET_SYSTEM_CHOICE_REMOVE = 3;
    private static final int BUDGET_SYSTEM_CHOICE_LOAD_PREV = 4;

    public static void main(String[] args) throws IOException {
        System.out.print("Budget System Name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        BudgetSystem myBudgets = new BudgetSystem(name);

        int choiceFromBudgetSystem = budgetSystemChoice(myBudgets);
        while (choiceFromBudgetSystem != BUDGET_SYSTEM_CHOICE_EXIT) {
            if (choiceFromBudgetSystem == BUDGET_SYSTEM_CHOICE_SELECT) {
                selectCategoryChoice(myBudgets);
            } else if (choiceFromBudgetSystem == BUDGET_SYSTEM_CHOICE_ADD) {
                addCategory(myBudgets);
            } else if (choiceFromBudgetSystem == BUDGET_SYSTEM_CHOICE_REMOVE) {
                removeCategory(myBudgets);
            } else if (choiceFromBudgetSystem == BUDGET_SYSTEM_CHOICE_LOAD_PREV) {
                myBudgets = loadPreviousBudgetSystem();
            }
            choiceFromBudgetSystem = budgetSystemChoice(myBudgets);
        }
        saveIfNeeded(myBudgets);

    }

    /*
     * REQUIRES: budget system must exist (not be null)
     * MODIFIES: ./data/appBudgetSystem.json
     * EFFECTS: saves data to the json file if user selects yes
     */
    private static void saveIfNeeded(BudgetSystem myBudgets) throws FileNotFoundException {
        System.out.println("-----------------------------");
        System.out.println("Would you like to save?");
        System.out.println("Y = Yes");
        System.out.println("N = No");
        System.out.println();
        System.out.print("Enter choice: ");

        Scanner input = new Scanner(System.in);
        String saveInput = input.nextLine();

        //when the category the user inputs does not exist...
        while (!saveInput.equalsIgnoreCase("Y") && !saveInput.equalsIgnoreCase("N")) {
            System.out.print("Invalid input. Please re-enter: ");
            saveInput = input.nextLine();
        }

        if (saveInput.equalsIgnoreCase("Y")) {
            JsonWriter writer = new JsonWriter("./data/appBudgetSystem.json");
            writer.open();
            writer.write(myBudgets);
            writer.close();
        }
    }

    /*
     * REQUIRES: json file must exist
     * EFFECTS: returns previous saved budget system
     */
    private static BudgetSystem loadPreviousBudgetSystem() throws IOException {
        JsonReader reader = new JsonReader("./data/appBudgetSystem.json");
        return reader.read();
    }

    /*
     * REQUIRES: budget system must exist (not be null)
     * MODIFIES: myBudget
     * EFFECTS: removes chosen category in budget system
     */
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

    /*
     * REQUIRES: budget system must exist (not be null)
     * MODIFIES: myBudget
     * EFFECTS: adds given category to budget system
     */
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

    /*
     * REQUIRES: budget system must exist (not be null)
     * EFFECTS: returns user's choice from budget system choices
     */
    private static int budgetSystemChoice(BudgetSystem budgetSystem) {
        displayBudgetSystemContent(budgetSystem);

        displayChoices("1 = Select Category", "2 = Add Category",
                "3 = Remove Category", "4 = Load Previous Budget System");

        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();

        while (userChoice > 4 || userChoice < 0) {
            System.out.print("Error. Please re-enter: ");
            userChoice = input.nextInt();
        }
        return userChoice;
    }

    /*
     * REQUIRES: budget system must exist (not be null)
     * EFFECTS: displays content of budget system
     */
    private static void displayBudgetSystemContent(BudgetSystem budgetSystem) {
        System.out.println("-----------------------------");
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

    /*
     * EFFECTS: displays choices for user to select from
     */
    private static void displayChoices(String s1, String s2, String s3, String s4) {
        System.out.println("-----------------------------");
        System.out.println("Choices: ");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        if (null != s4 && s4.length() > 0) {
            System.out.println(s4);
        }
        System.out.println("0 = Exit");
        System.out.println();
        System.out.print("Enter choice: ");
    }

    /*
     * REQUIRES: budget system must exist (not be null)
     * EFFECTS: reads user input and performs chosen task
     */
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

    /*
     * REQUIRES: chosen category must exist
     * MODIFIES: chosenCategory
     * EFFECTS: modifies budget of selected category
     */
    private static void modifyBudget(Category chosenCategory) {
        System.out.println("-----------------------------");
        System.out.print("Please enter new budget: ");

        Scanner input = new Scanner(System.in);
        double budget = input.nextDouble();

        chosenCategory.setBudget(budget);
    }

    /*
     * REQUIRES: chosen category must exist
     * MODIFIES: chosenCategory
     * EFFECTS: adds expense to selected category
     */
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

    /*
     * REQUIRES: chosen category must exist
     * MODIFIES: chosenCategory
     * EFFECTS: resets budget of selected category
     */
    private static void resetBalance(Category chosenCategory) {
        chosenCategory.resetBalance();
    }

    /*
     * REQUIRES: chosen category must exist
     * MODIFIES: chosenCategory
     * EFFECTS: helper function, actually performs selected task for selected category
     */
    private static void maintainCategory(Category chosenCategory) {
        displayCategoryContent(chosenCategory);
        displayChoices("1 = Modify Budget", "2 = Add Expense", "3 = Reset Balance", "");

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

    /*
     * REQUIRES: chosen category must exist
     * EFFECTS: displays content of all categories
     */
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
