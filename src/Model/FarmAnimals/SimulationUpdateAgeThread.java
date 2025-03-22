package Model.FarmAnimals;

import java.util.Iterator;

import Model.AgeState;
import Model.Farm;
import Model.Entity;

/**
 * Thread de simulation qui met à jour l'état du modèle.
 * Ce thread parcourt toutes les entités de la ferme et appelle updateAge()
 * sur les objets de type FarmAnimal à intervalles réguliers.
 */
public class SimulationUpdateAgeThread extends Thread { //TODO à documenter en anglais
    // Délai d'update en millisecondes (5 secondes)
    public static final int UPDATE_DELAY = 5000;
    private Farm farm;

    /**
     * Constructeur qui initialise le thread avec la ferme.
     * @param farm la ferme contenant les entités à mettre à jour.
     */
    public SimulationUpdateAgeThread(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void run() {
        while (true) {
            // Parcours de toutes les entités et mise à jour de l'âge pour chaque animal
            Iterator<Entity> entity = farm.getEntities();
            while (entity.hasNext()) {
                Entity e = entity.next();
                // On vérifie si l'entité est un animal de ferme avant d'appeler updateAge()
                if (e instanceof FarmAnimal) {
                    ((FarmAnimal) e).updateAge();
                    if (((FarmAnimal) e).getState() == AgeState.DEAD) {
                        e.getPosition().setIsTraversable(true);  // On libère la case
                        entity.remove(); // Si l'animal est mort, on le supprime de la ferme
                    }
                }
            }
            try {
                Thread.sleep(UPDATE_DELAY); // Pause 5s avant la prochaine mise à jour
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}