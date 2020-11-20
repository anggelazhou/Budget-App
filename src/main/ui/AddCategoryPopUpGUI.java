package ui;

import model.Category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryPopUpGUI extends AddPopUpGUI {

    private MainGUI mainGUI;

    public AddCategoryPopUpGUI(MainGUI mainGUI, String title, boolean modal) {
        super(mainGUI, title, modal);
        this.mainGUI = mainGUI;
    }

    @Override
    protected String getPopUpType() {
        return "Add Category";
    }

    @Override
    protected String getLabel1Text() {
        return "Name: ";
    }

    @Override
    protected String getLabel2Text() {
        return "Budget: ";
    }

    @Override
    protected ActionListener addPopUpTypeListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = new Category(textField1.getText(), Double.parseDouble(textField2.getText()));
                AddCategoryPopUpGUI.this.dispose();
                mainGUI.getBudgetSystem().addCategory(category);
                mainGUI.refreshBudgetSystemContentPanel();
            }
        };
    }


}
