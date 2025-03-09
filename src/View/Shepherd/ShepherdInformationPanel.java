package View.Shepherd;

import Model.Shepherd;
import View.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class ShepherdInformationPanel extends JPanel {
    //This class corresponds to displayer of the shepherd's information such as bank balance, salary, etc.
    // (on the bottom left side of the screen)
    private Shepherd s;
    public ShepherdInformationPanel(Shepherd s){
        super();

        this.s = s;
        setBackground(new Color(0x03071e));

        displayShepherdInfo();
    }
    private void displayShepherdInfo() {
        setLayout(new BorderLayout());

        // Display Shepherd's name
        JLabel nameLabel = new JLabel("Name: " + s.getName());
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel, BorderLayout.NORTH);

        // Display Shepherd's image
        //JLabel imageLabel = new JLabel(new ImageIcon(s.getImagePath()));
        //add(imageLabel, BorderLayout.CENTER);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(new Dimension(getParent().getWidth() ,ControlPanel.H_INFO_PANEL));
    }

}
