package View;

import javax.swing.*;
import java.awt.*;

public class Land extends JPanel {
    public Land(){
        super();
        assert(getParent() != null);
        setBackground(new Color(0x03071e));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(getParent().getSize());
    }
}
