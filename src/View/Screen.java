package View;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public static void main(String[] args){
        new Screen();
    }
    public Screen(){
        super();
        setTitle("Farm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setLayout(null);
        add(new World());
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
