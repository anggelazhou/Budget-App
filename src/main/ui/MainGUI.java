package ui;

import model.BudgetSystem;
import model.Category;
import model.Expense;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private BudgetSystem budgetSystem;
    private ThemeUI theme;

    public MainGUI() {
        super("Budget System");
        initializeGraphics();
    }

    public static void main(String[] args) {
        new MainGUI();
    }

    private void initializeGraphics() {
        // TODO: delete below
        budgetSystem = prepareBudgetSystem();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        theme = new ThemeUI();
        setLayout(new BorderLayout(10, 10));
        add(createBudgetSystemNamePanel(), BorderLayout.PAGE_START);
        add(createBudgetSystemContentPanel(), BorderLayout.CENTER);
        add(createBudgetSystemExit(), BorderLayout.PAGE_END);
        setVisible(true);
    }

    // TEMPORARY, FOR DESIGN PURPOSES
    private BudgetSystem prepareBudgetSystem() {
        BudgetSystem ret = new BudgetSystem("November's Budget");
        Category food = new Category("food", 120.0);
        Expense burger = new Expense("burger", 6.0);
        food.addExpense(burger);
        ret.addCategory(food);
        Category car = new Category("car", 400.0);
        ret.addCategory(car);
        return ret;
    }

    private JPanel createBudgetSystemNamePanel() {
        JPanel ret = new JPanel();
        ret.setBorder(theme.emptyBorder());
        ret.setLayout(new GridLayout(2, 1, 0, -10));

        JLabel lblBudgetSystemName = new JLabel();
        lblBudgetSystemName.setText(budgetSystem.getName());
        lblBudgetSystemName.setFont(theme.headerFont());

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAddCategory = new JButton();
        btnAddCategory.setText("Add Category");
        btnPanel.add(btnAddCategory);

        ret.add(lblBudgetSystemName);
        ret.add(btnPanel);

        return ret;
    }

    // NEEDS A LOT OF FIXING
    private JPanel createBudgetSystemContentPanel() {

        JPanel ret = new JPanel();
        ret.setBorder(theme.emptyBorder());
        ret.setLayout(new GridLayout(budgetSystem.getCategories().size(), 1, 0,30 ));

        for (Category c : budgetSystem.getCategories()) {
            CategoryPanel categoryPanel = new CategoryPanel(c, theme);
            ret.add(categoryPanel);
        }

        return ret;
    }

    private JPanel createBudgetSystemExit() {

        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(1, 2));

        JButton loadPreviousBudgetSystem = new JButton();
        loadPreviousBudgetSystem.setText("Load Previous Budget System");

        JButton exitBudgetSystem = new JButton();
        exitBudgetSystem.setText("Exit");

        ret.add(loadPreviousBudgetSystem);
        ret.add(exitBudgetSystem);

        return ret;
    }

}
