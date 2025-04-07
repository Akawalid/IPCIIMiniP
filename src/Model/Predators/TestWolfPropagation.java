package Model.Predators;

import Model.Entity;
import Model.Farm;
import Model.Predators.Wolf;

import java.util.Iterator;

public class TestWolfPropagation {
    public static void main(String[] args) {
        // Création de la ferme et initialisation du terrain
        Farm farm = new Farm();

        // Génère les dens (dens de loups et de renards) et démarre leur thread
        farm.initPredators();

        // Boucle d'observation : toutes les 5 secondes, on compte le nombre de wolves
        for (int i = 0; i < 6; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int wolfCount = 0;
            for (Iterator<Entity> it = farm.getEntities(); it.hasNext(); ) {
                Entity e = it.next();
                if (e instanceof Wolf) {
                    wolfCount++;
                }
            }
            System.out.println("Après " + ((i + 1) * 5) + " secondes, nombre de wolves : " + wolfCount);
        }

        System.out.println("Test terminé.");
    }
}
