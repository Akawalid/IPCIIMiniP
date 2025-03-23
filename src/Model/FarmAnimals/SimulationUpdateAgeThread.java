package Model.FarmAnimals;

import java.util.Iterator;

import Model.AgeState;
import Model.Farm;
import Model.Entity;

/**
 * Thread de simulation qui met � jour l'�tat du mod�le.
 * Ce thread parcourt toutes les entit�s de la ferme et appelle updateAge()
 * sur les objets de type FarmAnimal � intervalles r�guliers.
 */
public class SimulationUpdateAgeThread extends Thread { //TODO � documenter en anglais
    // D�lai d'update en millisecondes (5 secondes)
    public static final int UPDATE_DELAY = 5000;
    private Farm farm;

    /**
     * Constructeur qui initialise le thread avec la ferme.
     * @param farm la ferme contenant les entit�s � mettre � jour.
     */
    public SimulationUpdateAgeThread(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void run() {
        while (true) {
            // Parcours de toutes les entit�s et mise � jour de l'�ge pour chaque animal
            Iterator<Entity> entity = farm.getEntities();
            while (entity.hasNext()) {
                Entity e = entity.next();
                // On v�rifie si l'entit� est un animal de ferme avant d'appeler updateAge()
                if (e instanceof FarmAnimal) {
                    ((FarmAnimal) e).updateAge();
                    if (((FarmAnimal) e).getState() == AgeState.DEAD) {
                        e.getPosition().setIsTraversable(true);  // On lib�re la case
                        entity.remove(); // Si l'animal est mort, on le supprime de la ferme
                    }
                }
            }
            try {
                Thread.sleep(UPDATE_DELAY); // Pause 5s avant la prochaine mise � jour
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}