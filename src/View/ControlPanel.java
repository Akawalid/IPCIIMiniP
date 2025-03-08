package View;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private static final int W = 100;
    public ControlPanel(){
        super();
        assert(getParent() != null);
        setPreferredSize(new Dimension(W, getParent().getHeight()));
        setBackground(new Color(0x03071e));
    }
}
