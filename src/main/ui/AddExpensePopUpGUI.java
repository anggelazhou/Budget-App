package ui;

import model.Expense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpensePopUpGUI extends AddPopUpGUI {

    private SelectCategoryPopUpGUI categoryPopUpGUI;

    public AddExpensePopUpGUI(MainGUI owner, String title, boolean modal, SelectCategoryPopUpGUI category) {
        super(owner, title, modal);
        this.categoryPopUpGUI = category;
    }

    @Override
    protected String getPopUpType() {
        return "Add Expense";
    }

    @Override
    protected String getLabel1Text() {
        return "Description: ";
    }

    @Override
    protected String getLabel2Text() {
        return "Amount: ";
    }

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
