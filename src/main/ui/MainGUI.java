package ui;

import model.BudgetSystem;
import model.Category;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    // MODIFIES: this
    // EFFECTS:  sets budgetSystem to null, and creates border layout for name, content and exit button panel
    //           this method is called by the MainGUI constructor
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

    // EFFECTS: creates new empty budget system with given name
    private BudgetSystem prepareBudgetSystem() {
        String budgetSystemName = JOptionPane.showInputDialog("Name of Budget System: ");
        BudgetSystem ret = new BudgetSystem(budgetSystemName);
        return ret;
    }

    // EFFECTS: creates budget system name panel, which includes a budget system's name label
    // and add category button
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

    // EFFECTS: opens add category pop up
    private ActionListener openAddCategoryPopUp() {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCategoryPopUpGUI(MainGUI.this, "New Category", true)
                        .setVisible(true);
            }
        };
    }

    // EFFECTS: creates budget system content panel, which includes categories and
    // their corresponding balance and budget
    private JScrollPane createBudgetSystemContentPanel() {
        budgetSystemContentPanel = new JPanel();
        budgetSystemContentPanel.setBorder(theme.emptyBorder());

        refreshBudgetSystemContentPanel();

        return new JScrollPane(budgetSystemContentPanel);
    }

    // EFFECTS: refreshes budget system content panel to display changes (if any were made)
    public void refreshBudgetSystemContentPanel() {
        budgetSystemContentPanel.removeAll();

        // check if no categories (ex: if user removes all categories)
        if (budgetSystem.getCategories().size() == 0) {
            JLabel noCategoriesLabel = new JLabel();
            noCategoriesLabel.setText("No categories yet!");
            noCategoriesLabel.setFont(getTheme().contentFont());
            budgetSystemContentPanel.add(noCategoriesLabel);
        } else {
            budgetSystemContentPanel.setLayout(new GridLayout(
                    budgetSystem.getCategories().size(), 1, 0, 30));

            for (Category c : budgetSystem.getCategories()) {
                CategoryPanel categoryPanel = new CategoryPanel(c, this);
                budgetSystemContentPanel.add(categoryPanel);
            }
        }

        // force to redisplay
        budgetSystemContentPanel.revalidate();
    }

    // EFFECTS: creates budget system exit panels, which includes w buttons:
    //      1. load previous budget system
    //      2. exit
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

    // EFFECTS: loads previous budget system
    private ActionListener btnLoadPreviousBudgetSystemActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReader reader = new JsonReader("./data/appBudgetSystem.json");
                try {
                    budgetSystem = reader.read();
                    refreshBudgetSystemContentPanel();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
    }

    // EFFECTS: creates pop up asking if user would like to save, then saves if necessary and exits
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

    // EFFECTS: saves budget system
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

    // EFFECTS: gets budget system
    public BudgetSystem getBudgetSystem() {
        return budgetSystem;
    }

    // EFFECTS: gets theme
    public ThemeUI getTheme() {
        return theme;
    }


}
