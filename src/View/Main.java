package View;

import Model.Farm;

import javax.swing.*;
import java.awt.*;

public class Main {
    //This class handles the whole screen with different pages, Menu, World, GameOver, etc.
    public static void main(String[] args){
        JFrame fr = new JFrame();
        Farm farm = new Farm();
        fr.setTitle("Farm");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.add(new World(farm));
        fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}