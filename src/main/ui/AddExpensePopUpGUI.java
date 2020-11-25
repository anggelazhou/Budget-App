package ui;

import model.Expense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the add expense pop up from the category panel
public class AddExpensePopUpGUI extends AddPopUpGUI {

    private SelectCategoryPopUpGUI categoryPopUpGUI;

    public AddExpensePopUpGUI(MainGUI owner, String title, boolean modal, SelectCategoryPopUpGUI category) {
        super(owner, title, modal);
        this.categoryPopUpGUI = category;
    }

    // EFFECTS: gets pop up type of "Add Expense"
    @Override
    protected String getPopUpType() {
        return "Add Expense";
    }

    // EFFECTS: gets first label string
    @Override
    protected String getLabel1Text() {
        return "Description: ";
    }

    // EFFECTS: gets second label string
    @Override
    protected String getLabel2Text() {
        return "Amount: ";
    }

    // EFFECTS: adds expense to category's expense list and updates balance and expense table accordingly
    @Override
    protected ActionListener addPopUpTypeListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryPopUpGUI.getCategory().addExpense(new Expense(textField1.getText(),
                        Double.parseDouble(textField2.getText())));
                categoryPopUpGUI.refreshBalanceAndExpenseTable();
                AddExpensePopUpGUI.this.dispose();
            }
        };
    }
}
