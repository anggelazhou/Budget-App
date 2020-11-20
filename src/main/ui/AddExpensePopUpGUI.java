package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddExpensePopUpGUI extends AddPopUpGUI {

    public AddExpensePopUpGUI(MainGUI owner, String title, boolean modal) {
        super(owner, title, modal);
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
        return null;
    }
}
