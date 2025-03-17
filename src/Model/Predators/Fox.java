//// Fichier : Model/Predators/Fox.java
//package Model.Predators;
//
//import Model.FarmAnimals.Hen;
//import Model.Exceptions.InvalidCoordinates;
//import Model.FarmAnimals.Hen;
//import Model.Spot;
//import java.awt.*;
//
//public class Fox extends Predator {
//
//    public Fox(String name) {
//        super(name);
//        this.aggressivity = 3; // Valeur d'exemple, g�n�ralement moins agressif que le loup
//        this.patience = 8;
//        this.speed = aggressivity * 2;
//    }
//
//    public Fox(Spot position, String name) {
//        super(position, name);
//        this.aggressivity = 3;
//        this.patience = 8;
//        this.speed = aggressivity * 2;
//    }
//
//    @Override
//    public Class getTargetPreyClass() {
//        // Les renards chassent les poules/coqs
//        return Hen.class;
//    }
//}
