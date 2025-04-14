package View;


import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Main {
    //This class handles the whole screen with different pages, Menu, World, GameOver, etc.

    public static void main(String[] args){

        JFrame fr = new JFrame();

        PrincipalPanel p = new PrincipalPanel();
        fr.add(p);

        Controller crts = new Controller(p);

        Refresh refresh = new Refresh(p);


        fr.setTitle("Farm");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);

        //lancement des threads
        refresh.start();
    }
}