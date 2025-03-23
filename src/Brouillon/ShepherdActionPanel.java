//package Brouillon;
//
//import Model.Shepherd.Shepherd;
//import View.CustomButton;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class ShepherdActionPanel extends JPanel {
//    //This class corresponds to displayer of the shepherd's actions (on the bottom left side of the screen)
//    private static final int MARGIN = 5;
//    private CustomButton moveButton, collectButton, putButton;
//    private Shepherd s;
//    public ShepherdActionPanel(Shepherd s){
//        super();
//        this.s = s;
//
//        setBackground(new Color(0x03071e));
//
//        moveButton = new CustomButton("Move");
//        collectButton = new CustomButton("Collect");
//        putButton = new CustomButton("Put");
//
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        add(moveButton);
//        add(Box.createRigidArea(new Dimension(0, MARGIN)));
//        add(collectButton);
//        add(Box.createRigidArea(new Dimension(0, MARGIN)));
//        add(putButton);
//
//
//        //We add a controller to the button.
//        moveButton.addMouseListener(new ReactionMoveShepherd(s));
//    }
//}
