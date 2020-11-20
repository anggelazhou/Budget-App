package ui;

import model.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCategoryPopUpGUI extends JDialog {

    private MainGUI mainGUI;
    private Category category;
    private JLabel lblBalance;
    private JLabel lblBudget;
    private JComponent content;

    public SelectCategoryPopUpGUI(MainGUI owner, Category category) {
        super(owner, category.getName(), true);
        this.mainGUI = owner;
        this.category = category;
        centrePopUp(owner);
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: centres pop up on middle of budget system window
    // this method is called by the SelectCategoryPopUpGUI constructor
    private void centrePopUp(JFrame owner) {
        final int w = 600;
        final int h = 400;
        int posX = owner.getX() + (owner.getWidth() - w) / 2;
        int posY = owner.getY() + (owner.getHeight() - h) / 2;

        setSize(new Dimension(w, h));
        setLocation(posX, posY);
    }

    // MODIFIES: this
    // EFFECTS: creates border layout for title, content and button panel
    // this method is called by the SelectCategoryPopUpGUI constructor
    public void initializeGraphics() {
        setLayout(new BorderLayout(10, 10));

        // Title
        JPanel info = setUpCategoryInfoPanel();
        add(info, BorderLayout.PAGE_START);

        // Content
        content = setUpCategoryContentTable();
        add(content, BorderLayout.CENTER);
        content.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));

        // Button
        JPanel buttons = setUpButtons();
        add(buttons, BorderLayout.PAGE_END);
    }

    // EFFECTS: creates category info panel, including a label of the category name, as well as the category details
    // (which is another panel created through a helper method)
    private JPanel setUpCategoryInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        JLabel lblCategoryName = new JLabel();
        lblCategoryName.setText(category.getName());
        lblCategoryName.setFont(mainGUI.getTheme().headerFont());
        infoPanel.add(lblCategoryName, BorderLayout.LINE_START);

        JPanel categoryDetailsPanel = setUpCategoryDetailsPanel();
        infoPanel.add(categoryDetailsPanel, BorderLayout.LINE_END);

        return infoPanel;
    }

    // EFFECTS: creates category details panel, which includes a label of the category's balance and budget,
    // as well as an add expense button
    private JPanel setUpCategoryDetailsPanel() {
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(3, 1));

        lblBalance = new JLabel();
        lblBalance.setText("Balance: " + category.calculateBalance());
        lblBalance.setFont(mainGUI.getTheme().contentFont());

        lblBudget = new JLabel();
        lblBudget.setText("Budget: " + category.getBudget());
        lblBudget.setFont(mainGUI.getTheme().contentFont());

        JButton btnAddExpense = new JButton();
        btnAddExpense.setText("Add Expense");
        btnAddExpense.addActionListener(openAddExpensePopUp());
        btnAddExpense.setFont(mainGUI.getTheme().contentFont());

        detailPanel.add(lblBalance);
        detailPanel.add(lblBudget);
        detailPanel.add(btnAddExpense);
        return detailPanel;

    }

    // EFFECTS: sets up expense table
    private JComponent setUpCategoryContentTable() {
        String[] columnNames = {"#", "Description", "Amount ($)"};

        Object[][] data = buildTableData();

        // deal with 0 expenses case
        if (data.length == 0) {
            return new JLabel("No expenses yet!");

        } else {
            JTable expensesTable = new JTable(data, columnNames);

            return new JScrollPane(expensesTable);
        }
    }

    // EFFECTS: sets up data for expense table
    private Object[][] buildTableData() {
        Object[][] data = new Object[category.getExpenses().size()][];

        for (int i = 0; i < category.getExpenses().size(); i++) {
            data[i] = new Object[3];
            data[i][0] = i + 1;
            data[i][1] = category.getExpenses().get(i).getDescription();
            data[i][2] = category.getExpenses().get(i).getAmount();
        }

        return data;
    }

    // EFFECTS: sets up button panel, including a reset balance, modify budget and close button
    private JPanel setUpButtons() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnResetBalance = new JButton();
        btnResetBalance.setText("Reset Balance");
        btnResetBalance.setFont(mainGUI.getTheme().contentFont());
        btnResetBalance.addActionListener(resetBalanceActionListener());

        JButton btnModifyBudget = new JButton();
        btnModifyBudget.setText("Modify Budget");
        btnModifyBudget.setFont(mainGUI.getTheme().contentFont());
        btnModifyBudget.addActionListener(modifyBudgetActionListener());

        JButton btnClose = new JButton();
        btnClose.setText("Close");
        btnClose.setFont(mainGUI.getTheme().contentFont());
        btnClose.addActionListener(closeListener());

        btnPanel.add(btnResetBalance);
        btnPanel.add(btnModifyBudget);
        btnPanel.add(btnClose);
        return btnPanel;
    }

    // EFFECTS: clears all expenses of category, which also resets balance
    private ActionListener resetBalanceActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                category.getExpenses().clear();
                refreshBalanceAndExpenseTable();
            }
        };
    }

    // EFFECTS: refreshes balance and expense table to display changes (if any were made)
    public void refreshBalanceAndExpenseTable() {
        // refresh balance
        lblBalance.setText("Balance: " + category.calculateBalance());

        // refresh expenses table
        remove(content);
        content = setUpCategoryContentTable();
        add(content, BorderLayout.CENTER);
    }

    // EFFECTS: changes budget of category to new given budget
    private ActionListener modifyBudgetActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBudget = JOptionPane.showInputDialog("Enter new budget: ");
                if (newBudget != null && newBudget.length() > 0) {
                    category.setBudget(Double.parseDouble(newBudget));

                    // refresh budget
                    lblBudget.setText("Budget: " + category.getBudget());

                    // refresh balance
                    lblBalance.setText("Balance: " + category.calculateBalance());
                }
            }
        };
    }

    // EFFECTS: closes current pop up (of selected category)
    private ActionListener closeListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCategoryPopUpGUI.this.dispose();
                mainGUI.refreshBudgetSystemContentPanel();
            }
        };
    }

    // EFFECTS: opens add expense pop up
    private ActionListener openAddExpensePopUp() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddExpensePopUpGUI(mainGUI, "New Expense", true, SelectCategoryPopUpGUI.this)
                        .setVisible(true);
            }
        };
    }

    // EFFECTS: gets category
    public Category getCategory() {
        return category;
    }
}
