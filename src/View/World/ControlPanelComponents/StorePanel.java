package View.World.ControlPanelComponents;

import javax.swing.*;

public class StorePanel extends JPanel {
    //World is a layeredPane, the store and the bank will be displayed as pop up's on the screen that why we use a layeredPane.
    //It is important to note that LayeredPane doesnt not support setPereferredSize, it uses a null Layout, so we have to set
    //the bounds of each element inside manually.
    public static final int STORE_W = 500, STORE_H = 400;
    public StorePanel(){
        super();
    }

    public void update(){
        //TODO
    }
}
