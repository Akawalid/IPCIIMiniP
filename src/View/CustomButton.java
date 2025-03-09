package View;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    //The game's bettuon will all be instances of this class, it makes it easy to factorize the code, if we would add
    //animations, ...
    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(Color.GRAY);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Color.GRAY);
            }
        });
    }


}
