package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Represents theme of GUI (font)
public class ThemeUI {

    // EFFECTS: creates font for budget system and category name
    public Font headerFont() {
        return new Font("Serif", Font.BOLD, 20);
    }

    // EFFECTS: creates font for all content
    public Font contentFont() {
        return new Font("Serif", Font.PLAIN, 14);
    }

    // EFFECTS: creates empty border
    public Border emptyBorder() {
        return BorderFactory.createEmptyBorder(5, 20, 10, 20);
    }
}
