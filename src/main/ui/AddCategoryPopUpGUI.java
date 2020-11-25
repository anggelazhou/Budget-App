package ui;

import model.Category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the add category pop up from the main panel
public class AddCategoryPopUpGUI extends AddPopUpGUI {

    private MainGUI mainGUI;

    public AddCategoryPopUpGUI(MainGUI mainGUI, String title, boolean modal) {
        super(mainGUI, title, modal);
        this.mainGUI = mainGUI;
    }

    // EFFECTS: gets pop up type of "Add Category"
    @Override
    protected String getPopUpType() {
        return "Add Category";
    }

    // EFFECTS: gets first label string
    @Override
    protected String getLabel1Text() {
        return "Name: ";
    }

    // EFFECTS: gets second label string
    @Override
    protected String getLabel2Text() {
        return "Budget: ";
    }

    // EFFECTS: adds category to budget system and refreshes content panel accordingly
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
