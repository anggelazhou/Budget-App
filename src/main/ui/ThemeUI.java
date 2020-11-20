package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ThemeUI {

    // For budget system and category name
    public Font headerFont() {
        return new Font("Serif", Font.BOLD, 20);
    }

    // For all content
    public Font contentFont() {
        return new Font("Serif", Font.PLAIN, 14);
    }

    public Border emptyBorder() {
        return BorderFactory.createEmptyBorder(5, 20, 10, 20);
    }
}
