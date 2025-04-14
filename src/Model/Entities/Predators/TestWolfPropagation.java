package Model.Entities.Predators;

import Model.Entities.Entity;
import Model.Farm;

import java.util.Iterator;

public class TestWolfPropagation {
    public static void main(String[] args) {
        // Cr�ation de la ferme et initialisation du terrain
        Farm farm = new Farm();

        // G�n�re les dens (dens de loups et de renards) et d�marre leur thread
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
            System.out.println("Apr�s " + ((i + 1) * 5) + " secondes, nombre de wolves : " + wolfCount);
        }

        System.out.println("Test termin�.");
    }

}
