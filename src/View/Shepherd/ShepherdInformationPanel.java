package View.Shepherd;

import View.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class ShepherdInformationPanel extends JPanel {
    //This class corresponds to displayer of the shepherd's information such as bank balance, salary, etc.
    // (on the bottom left side of the screen)
    public ShepherdInformationPanel(){
        super();
        setBackground(new Color(0x03071e));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(new Dimension(getParent().getWidth() ,ControlPanel.H_INFO_PANEL));
    }

}
