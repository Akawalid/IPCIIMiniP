package View.Shepherd;

import View.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class ShepherdActionPanel extends JPanel {
    //This class corresponds to displayer of the shepherd's actions (on the bottom left side of the screen)

    public ShepherdActionPanel(){
        super();
        setBackground(new Color(0x03071e));
    }
    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(new Dimension(getParent().getWidth() , ControlPanel.H_INFO_PANEL));
    }
}
