package ui;

import model.Category;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryPanel extends JPanel {
    private final Category category;
    private final MainGUI mainGUI;

    public CategoryPanel(Category category, MainGUI mainGUI) {
        this.category = category;
        this.mainGUI = mainGUI;
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 20),
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
        setLayout(new BorderLayout(10, 10));
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: creates border layout for name, content and button panel for each category
    // this method is called by the CategoryPanel constructor
    private void initializeGraphics() {
        // Name
        JLabel lblCategoryName = new JLabel();
        lblCategoryName.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 100));
        lblCategoryName.setFont(mainGUI.getTheme().headerFont());
        lblCategoryName.setText(category.getName());
        add(lblCategoryName, BorderLayout.LINE_START);

        // Content
        JPanel content = setUpContentPanel();
        add(content, BorderLayout.CENTER);

        // Button
        JPanel buttons = setUpButtons();
        add(buttons, BorderLayout.PAGE_END);
    }

    // EFFECTS: creates content panel, including a balance and budget label
    private JPanel setUpContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 30, 0));

        JLabel lblBalance = new JLabel();
        lblBalance.setText("Balance: $" + category.calculateBalance());
        lblBalance.setFont(mainGUI.getTheme().contentFont());

        JLabel lblBudget = new JLabel();
        lblBudget.setText("Budget: $" + category.getBudget());
        lblBudget.setFont(mainGUI.getTheme().contentFont());

        contentPanel.add(lblBalance);
        contentPanel.add(lblBudget);

        return contentPanel;
    }

    // EFFECTS: creates set up button panel, including remove and select button
    private JPanel setUpButtons() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnRemoveCategory = new JButton();
        btnRemoveCategory.setText("Remove");
        btnRemoveCategory.setFont(mainGUI.getTheme().contentFont());
        btnRemoveCategory.addActionListener(btnRemoveCategoryActionListener());

        JButton btnSelectCategory = new JButton();
        btnSelectCategory.setText("Select");
        btnSelectCategory.setFont(mainGUI.getTheme().contentFont());
        btnSelectCategory.addActionListener(openSelectCategoryPopUpGUI());

        btnPanel.add(btnRemoveCategory);
        btnPanel.add(btnSelectCategory);
        return btnPanel;
    }

    // EFFECTS: remove category from budget system, and refreshes budget system accordingly
    private ActionListener btnRemoveCategoryActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGUI.getBudgetSystem().removeCategory(category.getName());
                mainGUI.refreshBudgetSystemContentPanel();
            }
        };
    }

    // EFFECTS: opens details of selected category
    private ActionListener openSelectCategoryPopUpGUI() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectCategoryPopUpGUI(mainGUI, category).setVisible(true);
            }
        };
    }

}
