package View;

import Model.Farm;
import Controller.Controller;
import View.MainMenu.MainMenu;
import View.World.World;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel extends JPanel {
    private CardLayout cardLayout;
    public PrincipalPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
    }

    public void showCard(String cardName) {
        cardLayout.show(this, cardName);
    }
    public void closeApplication() {
        SwingUtilities.getWindowAncestor(this).dispose();
        System.exit(0);
    }

}