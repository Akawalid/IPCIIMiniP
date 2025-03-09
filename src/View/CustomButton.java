package View;

import javax.swing.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(true);
    }
}
