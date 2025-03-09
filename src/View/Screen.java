package View;

import Model.Farm;
import Model.Shepherd;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    //This class handles the whole screen with different pages, Menu, World, GameOver, etc.
    private Farm farm;
    public static void main(String[] args){

        //The font doesnt work for the moment I will correct it later.
        Font defaultFont = new Font("Mincraftia", Font.PLAIN, 24);
        UIManager.put("Button.font", defaultFont);       // Buttons
        UIManager.put("Label.font", defaultFont);        // Labels
        UIManager.put("TextField.font", defaultFont);    // Text fields
        UIManager.put("TextArea.font", defaultFont);     // Text areas
        UIManager.put("ComboBox.font", defaultFont);     // Combo boxes
        UIManager.put("List.font", defaultFont);         // Lists
        UIManager.put("Menu.font", defaultFont);         // Menus
        UIManager.put("MenuItem.font", defaultFont);     // Menu items
        UIManager.put("Table.font", defaultFont);        // Tables
        UIManager.put("Tree.font", defaultFont);         // Trees
        UIManager.put("TabbedPane.font", defaultFont);   // Tabbed panes
        UIManager.put("TitledBorder.font", defaultFont); // Titled borders

        Farm fr = new Farm();

        new Screen(fr);
    }
    public Screen(Farm farm){
        super();
        this.farm = farm;

        setTitle("Farm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setLayout(null);
        add(new World(farm));
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
