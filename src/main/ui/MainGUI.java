package ui;

import model.BudgetSystem;
import model.Category;
import model.Expense;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MainGUI extends JFrame {
    private BudgetSystem budgetSystem;

    private ThemeUI theme;
    private JPanel budgetSystemContentPanel;

    public MainGUI() {
        super("Budget System");
        initializeGraphics();
    }

    public static void main(String[] args) {
        new MainGUI();
    }

    private void initializeGraphics() {
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
        // TODO: delete below
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
        btnAddCategory.addActionListener(openAddCategoryPopUp());

        ret.add(lblBudgetSystemName);
        ret.add(btnPanel);

        return ret;
    }

    private ActionListener openAddCategoryPopUp() {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCategoryPopUpGUI(MainGUI.this, "New Category", true)
                        .setVisible(true);
            }
        };
    }

    private JScrollPane createBudgetSystemContentPanel() {
        budgetSystemContentPanel = new JPanel();
        budgetSystemContentPanel.setBorder(theme.emptyBorder());

        refreshBudgetSystemContentPanel();

        return new JScrollPane(budgetSystemContentPanel);
    }

    public void refreshBudgetSystemContentPanel() {
        budgetSystemContentPanel.removeAll();

        budgetSystemContentPanel.setLayout(new GridLayout(
                budgetSystem.getCategories().size(), 1, 0, 30));

        for (Category c : budgetSystem.getCategories()) {
            CategoryPanel categoryPanel = new CategoryPanel(c, this);
            budgetSystemContentPanel.add(categoryPanel);
        }

        budgetSystemContentPanel.revalidate();
    }

    private JPanel createBudgetSystemExit() {

        JPanel ret = new JPanel();
        ret.setLayout(new GridLayout(1, 2));

        JButton btnLoadPreviousBudgetSystem = new JButton();
        btnLoadPreviousBudgetSystem.setText("Load Previous Budget System");
        btnLoadPreviousBudgetSystem.addActionListener(btnLoadPreviousBudgetSystemActionListener());

        JButton btnExitBudgetSystem = new JButton();
        btnExitBudgetSystem.setText("Exit");
        btnExitBudgetSystem.addActionListener(btnExitBudgetSystemActionListener());

        ret.add(btnLoadPreviousBudgetSystem);
        ret.add(btnExitBudgetSystem);

        return ret;
    }

    private ActionListener btnLoadPreviousBudgetSystemActionListener() {
        // TODO
        return null;
    }

    private ActionListener btnExitBudgetSystemActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No", "Cancel"};

                int answer = JOptionPane.showOptionDialog(MainGUI.this,
                        "Would you like to save?", "Confirm Exit", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                if (answer == 0) {
                    saveToFile();
                    MainGUI.this.dispose();
                } else if (answer == 1) {
                    MainGUI.this.dispose();
                } else {
                    // cancelled
                }
            }
        };
    }

    private void saveToFile() {
        JsonWriter writer = new JsonWriter("./data/appBudgetSystem.json");
        try {
            writer.open();
            writer.write(budgetSystem);
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public BudgetSystem getBudgetSystem() {
        return budgetSystem;
    }

    public ThemeUI getTheme() {
        return theme;
    }


}
