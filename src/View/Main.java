package View;

import Model.Farm;
import Model.FarmAnimals.Sheep;
import Model.Shepherd.FindPath;
import Model.Shepherd.Shepherd;
import Model.Shepherd.ShepherdMovementThread;

import javax.swing.*;
import java.awt.*;

public class Main {
    //This class handles the whole screen with different pages, Menu, World, GameOver, etc.
    public static void main(String[] args){
        //création d'une fenêtre
        JFrame fr = new JFrame();
        Farm farm = new Farm();

        //Crée la grille (Land) + panneau de contrôle (ControlPanel)
        World world = new World(farm);
        fr.add(world);

        //Créer un Shepherd
        Shepherd shepherd = new Shepherd("Shepherd");
        farm.addEntity(shepherd);
        //Créer un mouton
        Sheep shp = new Sheep("sheep1");
        farm.addEntity(shp);

        //positionner sur la grille
        //@TODO à terme le faire automatiquement
        shepherd.setPosition(farm.getSpot(0, 0));
        shp.setPosition(farm.getSpot(5, 5));


        //create obstacles on the map
        farm.getSpot(1, 1).setIsTraversable(false);
        farm.getSpot(2, 2).setIsTraversable(false);
        farm.getSpot(2, 3).setIsTraversable(false);

        //Gestion des déplacements
        ShepherdMovementThread thrd = new ShepherdMovementThread(shepherd);
        Refresh refresh = new Refresh(world);

        FindPath fp = new FindPath(farm);

        //création d'un déplacement
        shepherd.setPath(fp.findPath(shepherd.getPosition(), shp.getPosition()));


        //afficher les informations du mouton


        //création fenêtre fin
        fr.setTitle("Farm");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);

        //lancement des threads
        thrd.start();
        refresh.start();
    }
}