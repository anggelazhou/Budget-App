package ui;

import model.Category;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CategoryPanel extends JPanel {
    private final Category category;
    private final ThemeUI theme;

    public CategoryPanel(Category category, ThemeUI theme) {
        this.category = category;
        this.theme = theme;
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 20),
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
        setLayout(new BorderLayout(10, 10));
        initializeGraphics();
    }

    private void initializeGraphics() {
        // Name
        JLabel lblCategoryName = new JLabel();
        lblCategoryName.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 100));
        lblCategoryName.setFont(theme.headerFont());
        lblCategoryName.setText(category.getName());
        add(lblCategoryName, BorderLayout.LINE_START);

        // Content
        JPanel content = setUpContentPanel();
        add(content, BorderLayout.CENTER);

        // Button
        JPanel buttons = setUpButtons();
        add(buttons, BorderLayout.PAGE_END);
    }

    private JPanel setUpContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 30, 0));

        JLabel lblBalance = new JLabel();
        lblBalance.setText("Balance: $" + category.calculateBalance());
        lblBalance.setFont(theme.contentFont());

        JLabel lblBudget = new JLabel();
        lblBudget.setText("Budget: $" + category.getBudget());
        lblBudget.setFont(theme.contentFont());

        contentPanel.add(lblBalance);
        contentPanel.add(lblBudget);
        return contentPanel;
    }

    private JPanel setUpButtons() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnRemoveCategory = new JButton();
        btnRemoveCategory.setText("Remove");
        btnRemoveCategory.setFont(theme.contentFont());

        JButton btnSelectCategory = new JButton();
        btnSelectCategory.setText("Select");
        btnSelectCategory.setFont(theme.contentFont());

        btnPanel.add(btnRemoveCategory);
        btnPanel.add(btnSelectCategory);
        return btnPanel;
    }

}
