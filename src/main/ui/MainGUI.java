package ui;

import model.BudgetSystem;
import model.Category;
import model.Expense;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    private static BudgetSystem budgetSystem;

    public static void main(String[] args) {
        budgetSystem = prepareBudgetSystem();
        JFrame frame = createFrame();
        frame.setLayout(new BorderLayout());
        frame.add(createBudgetSystemNamePanel(), BorderLayout.PAGE_START);
        frame.add(createBudgetSystemContentPanel(), BorderLayout.CENTER);
        frame.add(createBudgetSystemExit(), BorderLayout.PAGE_END);
        frame.setVisible(true);


    }

    private static BudgetSystem prepareBudgetSystem() {
        BudgetSystem ret = new BudgetSystem("November's Budget");
        Category food = new Category("food", 120.0);
        Expense burger = new Expense("burger", 6.0);
        food.addExpense(burger);
        ret.addCategory(food);
        Category car = new Category("car", 400.0);
        ret.addCategory(car);
        return ret;
    }

    private static JFrame createFrame() {
        JFrame budgetSystemFrame = new JFrame("Budget System");
        budgetSystemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        budgetSystemFrame.setSize(new Dimension(800, 600));
        return budgetSystemFrame;
    }

    private static JPanel createBudgetSystemNamePanel() {
        JPanel ret = new JPanel();
        ret.setLayout(new BorderLayout());
        JLabel budgetSystemName = new JLabel();
        budgetSystemName.setText(budgetSystem.getName());
        JButton addCategory = new JButton();
        addCategory.setText("\u2295");
        ret.add(budgetSystemName, BorderLayout.LINE_START);
        ret.add(new JLabel(""), BorderLayout.CENTER);
        ret.add(new JLabel(""), BorderLayout.CENTER);
        ret.add(addCategory, BorderLayout.LINE_END);
        return ret;
    }

    // NEEDS A LOT OF FIXING
    private static JPanel createBudgetSystemContentPanel() {

        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(3, 2));

        JLabel foodCategoryName = new JLabel();
        foodCategoryName.setText(budgetSystem.selectCategory("food").getName());
        JLabel foodCategoryBalance = new JLabel();
        foodCategoryBalance.setText(String.valueOf(budgetSystem.selectCategory("food").calculateBalance()));

        JLabel carCategoryName = new JLabel();
        carCategoryName.setText(budgetSystem.selectCategory("car").getName());
        JLabel carCategoryBalance = new JLabel();
        carCategoryBalance.setText(String.valueOf(budgetSystem.selectCategory("car").calculateBalance()));


        JButton selectCategoryButton = new JButton("Select Category");



        ret.add(foodCategoryName);
        ret.add(foodCategoryBalance);
        ret.add(carCategoryName);
        ret.add(carCategoryBalance);
        ret.add(selectCategoryButton);

        return ret;
    }

    private static JButton createBudgetSystemExit() {
        return new JButton("Exit");
    }

}
