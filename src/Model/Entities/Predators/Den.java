package Model.Entities.Predators;

import Model.Farm;
import Model.Position.Positionnable;
import Model.Position.Spot;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract class representing a Den where predators can spawn.
 * This class implements the Runnable interface to allow for concurrent execution.
 */
public abstract class Den extends Positionnable implements Runnable {

    // Maximum number of predators allowed to be alive from this den at once
    private static final int MAX_PREDATORS = 2;
    protected Farm farm;
    protected boolean active;
    protected ArrayList<Predator> livingPredators;

    // Nouveau flag pour éviter de lancer plusieurs threads de désactivation simultanée
    private boolean tempDisableRunning = false;


    /**
     * Constructor for the Den.
     * @param s the Spot where the den is located
     * @param farm the Farm object that holds the current game state
     */
    public Den(Spot s, Farm farm) {
        super(s);

        this.farm = farm;
        this.active = true;
        livingPredators = new ArrayList<>();
    }

    /*public void reactToAreaChange() {
        active = this.position.getProtectedArea() <= 0;
    }*/

    /**
     * React to changes in the protected area of the spot.
     * If the area is protected (protectedArea > 0) and the den is active,
     * it is temporarily disabled for a set duration.
     */
   public synchronized void reactToAreaChange() {
        if (this.position.getProtectedArea() > 0) {
            // Si le den est actif et qu'aucune désactivation temporaire n'est en cours,
            // on déclenche la désactivation pendant 10 secondes.
            if (active && !tempDisableRunning) {
                disableTemporarily();
            }
            // Le den reste désactivé tant que la protection s'applique
        } else {
            // Si la zone n'est plus protégée et qu'aucune désactivation n'est en cours,
            // on réactive le den.
            if (!tempDisableRunning) {
                active = true;
            }
        }
    }

    /**
     * Private method to disable the den temporarily.
     * It starts a new thread that sleeps for a specified time (25 seconds in this case);
     * after the delay, if the area protection has been removed, the den is reactivated.
     */
    private synchronized void disableTemporarily() {
        tempDisableRunning = true;
        active = false;
        new Thread(() -> {
            try {
                Thread.sleep(25000); // Délai de 10 secondes
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            synchronized(this) {
                // Fin de la période de désactivation
                tempDisableRunning = false;
                // On réactive le den si la zone n'est plus protégée.
                if (this.position.getProtectedArea() <= 0) {
                    active = true;
                }
                // Sinon, il restera désactivé jusqu'à ce qu'un nouvel appel à reactToAreaChange() se produise.
            }
        }).start();
    }

    /**
     * Checks if the den is active.
     * @return true if active, false otherwise.
     */

    public boolean isActive(){return active;}

    /**
     * Abstract method to spawn a predator (such as a Wolf or Fox) from the den.
     * Concrete subclasses must provide an implementation.
     */
    protected abstract void spawnPredator();

    /**
     * Updates the list of living predators by removing those that are dead.
     */
    private void updateLivingPredators() {
        for(Iterator<Predator> iterator = livingPredators.iterator(); iterator.hasNext();) {
            Predator predator = iterator.next();
            if (predator.getIsDead())  iterator.remove();
        }
    }

    /**
     * Stops the den from deploying new predators and stops all predators spawned from this den.
     * This method should be called to gracefully shut down the den.
     */
    public void stopDen() {
        active = false;
        // Arrêtez également tous les prédateurs spawnés par ce dens
        for (Predator p : livingPredators) {
            p.kill();  // ou p.stopPredator(), selon votre implémentation
        }
    }

    /**
     * The run method for this Runnable.
     * This method continuously checks the protection level of the area,
     * updates the list of living predators, and spawns new predators if allowed.
     */
    @Override
    public void run() {
        while (active) {
            // Vérifier périodiquement le niveau de protection
            this.reactToAreaChange();
            // Mise à jour de la liste des prédateurs vivants et tentative de spawn
            updateLivingPredators();
            if (livingPredators.size() < MAX_PREDATORS && active) {
                spawnPredator();
            }
            try {
                Thread.sleep(6000); // Intervalle de spawn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }






}
