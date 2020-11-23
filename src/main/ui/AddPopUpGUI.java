package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddPopUpGUI extends JDialog {

    protected MainGUI mainGUI;
    protected JTextField textField1;
    protected JTextField textField2;

    public AddPopUpGUI(MainGUI owner, String title, boolean modal) {
        super(owner, title, modal);
        this.mainGUI = owner;
        centrePopUp(owner);
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: creates border layout for title, content and button panel
    // this method is called by the AddPopUpGUI constructor
    private void initializeGraphics() {
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel lblTitle = new JLabel();
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 100));
        lblTitle.setFont(mainGUI.getTheme().headerFont());
        lblTitle.setText(getPopUpType());
        add(lblTitle, BorderLayout.PAGE_START);

        // Content
        JPanel content = setUpContentPanel();
        add(content, BorderLayout.CENTER);
        content.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));

        // Button
        JPanel buttons = setUpButtons();
        add(buttons, BorderLayout.PAGE_END);

    }
    

    // MODIFIES: this
    // EFFECTS: centres pop up in centre of previous window
    private void centrePopUp(JFrame owner) {
        final int w = 400;
        final int h = 180;
        int posX = owner.getX() + (owner.getWidth() - w) / 2;
        int posY = owner.getY() + (owner.getHeight() - h) / 2;

        setSize(new Dimension(w, h));
        setLocation(posX, posY);
    }

    // EFFECTS: sets up content panel, including two labels (name/budget OR description/amount depending out output
    // from helper method), as well as two text fields
    private JPanel setUpContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 0, 10));

        JLabel label1 = new JLabel();
        label1.setText(getLabel1Text());
        label1.setFont(mainGUI.getTheme().contentFont());
        label1.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        textField1 = new JTextField();

        JLabel label2 = new JLabel();
        label2.setText(getLabel2Text());
        label2.setFont(mainGUI.getTheme().contentFont());
        label2.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        textField2 = new JTextField();

        contentPanel.add(label1);
        contentPanel.add(textField1);
        contentPanel.add(label2);
        contentPanel.add(textField2);

        return contentPanel;
    }

    // EFFECTS: set up buttons panel, including cancel and add expense or category button
    // (depending on output from abstract helper method)
    private JPanel setUpButtons() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnCancel = new JButton();
        btnCancel.setText("Cancel");
        btnCancel.setFont(mainGUI.getTheme().contentFont());
        btnCancel.addActionListener(cancelActionListener());

        JButton btnPopUpType = new JButton();
        btnPopUpType.setText(getPopUpType());
        btnPopUpType.setFont(mainGUI.getTheme().contentFont());
        btnPopUpType.addActionListener(addPopUpTypeListener());

        btnPanel.add(btnCancel);
        btnPanel.add(btnPopUpType);
        return btnPanel;
    }

    // EFFECTS: closes pop up
    private ActionListener cancelActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPopUpGUI.this.dispose();
            }
        };
    }

    // EFFECTS: gets pop up type, which then determines pop up name
    protected abstract String getPopUpType();

    // EFFECTS: gets first label
    protected abstract String getLabel1Text();

    // EFFECTS: gets second label
    protected abstract String getLabel2Text();

    // EFFECTS: adds (category or expense) to list
    protected abstract ActionListener addPopUpTypeListener();

}
