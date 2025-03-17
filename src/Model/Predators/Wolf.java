//// Fichier : Model/Predators/Wolf.java
//package Model.Predators;
//
//import Model.FarmAnimals.Sheep;
//import Model.Exceptions.InvalidCoordinates;
//import Model.Spot;
//import java.awt.*;
//
//public class Wolf extends Predator {
//
//    public Wolf(String name) {
//        super(name);
//        this.aggressivity = 5; // Valeur d'exemple
//        this.patience = 10;
//        this.speed = aggressivity * 2;
//    }
//
//    public Wolf(Spot position, String name) {
//        super(position, name);
//        this.aggressivity = 5;
//        this.patience = 10;
//        this.speed = aggressivity * 2;
//    }
//
//    @Override
//    public Class getTargetPreyClass() {
//        // Les loups chassent les brebis/moutons
//        return Sheep.class;
//    }
//}
