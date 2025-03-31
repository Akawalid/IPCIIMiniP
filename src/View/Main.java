package View;

import Model.Farm;
import Model.FarmAnimals.Sheep;
import Model.FarmAnimals.UpdateAgeThread;
import Model.Shepherd.Shepherd;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Main {
    //This class handles the whole screen with different pages, Menu, World, GameOver, etc.

    public static Shepherd SHEPHERD_TEST_1;
    public static Sheep SHEEP_TEST_1;

    public static void main(String[] args){
        //création d'une fenêtre
        JFrame fr = new JFrame();
        Farm farm = new Farm();

        //@TODO à terme choisir positions automatiquement (pas valeur brute)
        //Créer un Shepherd et placer sur la grille
        SHEPHERD_TEST_1 = new Shepherd(farm.getSpot(0, 0));
        farm.addEntity(SHEPHERD_TEST_1);
        //Créer un mouton
        SHEEP_TEST_1 = new Sheep(farm.getSpot(5, 5));
        farm.addEntity(SHEEP_TEST_1);
//        farm.setSelectedEntity(SHEPHERD_TEST_1);

        //Crée la grille (Land) + panneau de contrôle (ControlPanel)
        World world = new World(farm);
        world.entityGenerateMetaData(SHEPHERD_TEST_1);
        world.entityGenerateMetaData(SHEEP_TEST_1);
        fr.add(world);

        Controller controller = new Controller(farm, world);

        //create obstacles on the map
        farm.getSpot(1, 1).setIsTraversable(false);
        farm.getSpot(2, 2).setIsTraversable(false);
        farm.getSpot(2, 3).setIsTraversable(false);

        //Gestion des déplacements
        Refresh refresh = new Refresh(world);

//        FindPath fp = new FindPath(farm);
//
//        //création d'un déplacement
//        System.out.println("aaaaaaaaaaaaa size: "
//                + fp.findPath(SHEPHERD_TEST_1.getPosition(), SHEEP_TEST_1.getPosition()).size());
//
//        SHEPHERD_TEST_1.setPath(fp.findPath(SHEPHERD_TEST_1.getPosition(), SHEEP_TEST_1.getPosition()));


        //création fenêtre fin
        fr.setTitle("Farm");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);

        //lancement des threads
        refresh.start();

        // Lancement du thread de mise à jour de la simulation
        // Ce thread parcourt toutes les entités de la ferme et appelle updateAge() pour chaque animal.
        UpdateAgeThread simThread = new UpdateAgeThread(farm);
        simThread.start();
    }
}