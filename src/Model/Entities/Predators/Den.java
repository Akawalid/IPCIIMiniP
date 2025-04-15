package Model.Entities.Predators;

import Model.Farm;
import Model.Position.Positionnable;
import Model.Position.Spot;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Den extends Positionnable implements Runnable {
    private static final int MAX_WOLVES = 3;
    protected Farm farm;
    protected boolean active;
    protected ArrayList<Predator> livingPredators;

    // Nouveau flag pour éviter de lancer plusieurs threads de désactivation simultanée
    private boolean tempDisableRunning = false;

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
     * Méthode réagissant aux changements d'aire protégée sur le spot.
     * Si la zone est protégée (protectedArea > 0) et que le den est actif,
     * il se désactive pendant 8 secondes.
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
     * Méthode privée qui désactive le den pendant 8 secondes.
     * Un thread est lancé pour effectuer le délai, après quoi, si la zone n'est plus protégée,
     * le den est réactivé.
     */
    private synchronized void disableTemporarily() {
        tempDisableRunning = true;
        active = false;
        new Thread(() -> {
            try {
                Thread.sleep(20000); // Délai de 10 secondes
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


    public boolean isActive(){return active;}

    /**
     * M�thode abstraite qui spawn un pr�dateur (Wolf ou Fox) depuis le den.
     */
    protected abstract void spawnPredator();
    private void updateLivingPredators() {
        for(Iterator<Predator> iterator = livingPredators.iterator(); iterator.hasNext();) {
            Predator predator = iterator.next();
            if (predator.getIsDead())  iterator.remove();
        }
    }
    /*@Override
    public void run() {
        while(true) {
            updateLivingPredators();
            if(livingPredators.size() < MAX_WOLVES && active) spawnPredator();
            try {
                Thread.sleep(5000); // D�lai entre chaque spawn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }*/

    @Override
    public void run() {
        while (true) {
            // Vérifier périodiquement le niveau de protection
            this.reactToAreaChange();
            // Mise à jour de la liste des prédateurs vivants et tentative de spawn
            updateLivingPredators();
            if (livingPredators.size() < MAX_WOLVES && active) {
                spawnPredator();
            }
            try {
                Thread.sleep(5000); // Intervalle de spawn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
