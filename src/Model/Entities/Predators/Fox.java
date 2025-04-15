package Model.Entities.Predators;




import Model.Entities.Entity;
import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.Entities.FarmAnimals.FarmAnimal;
import Model.Position.FindPath;
import Model.Position.Spot;
import Model.Exceptions.InvalidCoordinates;

import java.util.*;

public class Fox extends Predator {

    public Fox(Spot s, Farm farm) {
        super(s, farm);
    }

    @Override
    public void move() throws InvalidCoordinates {
        super.move();
        if(position.getProtectedArea() > 0) kill();
    }
    @Override
    public void run() {
        Random rand = new Random();
        while (!isDead) {
            try {
                Thread.sleep(1000); // Pause d'une seconde entre chaque cycle
            } catch (InterruptedException e) {
                break;
            }

            if(pause) continue;
            // 1. V?rifier et tuer toute proie adjacente (Ewe ou Sheep)
            checkAndKillPrey();

            // 2. Chercher la proie la plus proche
            FarmAnimal target = findClosestPrey();
            if (target != null) {
                // Utiliser FindPath pour calculer le chemin vers la proie
                ArrayDeque<Spot> path = FindPath.findPath(this.getPosition(), target.getPosition());
                this.setPath(path);
                if (this.hasMovements() && !isDead) {
                    try {
                        this.move();
                    } catch (InvalidCoordinates e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Si aucune proie n'est d?tect?e, se d?placer al?atoirement
                List<Spot> freeSpots = new ArrayList<>();
                for (Spot neighbor : getAdjacentSpots(this.getPosition())) {
                    if (neighbor.isTraversable() && neighbor.getProtectedArea() == 0) {
                        freeSpots.add(neighbor);
                    }
                }
                if (!freeSpots.isEmpty()) {
                    Spot next = freeSpots.get(rand.nextInt(freeSpots.size()));
                    // Cr?er un chemin d'une seule case pour le d?placement
                    ArrayDeque<Spot> singleStep = new ArrayDeque<>();
                    singleStep.add(next);
                    this.setPath(singleStep);
                    if(!isDead){
                        try {
                            this.move();
                        } catch (InvalidCoordinates e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }
    protected void checkAndKillPrey() {
        for (Spot neighbor : getAdjacentSpots(this.getPosition())) {
            Entity entity = farm.getEntityInSpot(neighbor.getRow(), neighbor.getCol());
            if (entity instanceof FarmAnimal) { // entity != null &&
                String species = entity.getSpecies();
                // Pour le wolf, la proie peut ?tre une Ewe ou un Sheep
                if (species.equals("Hen")) {
//                    farm.removeEntity(entity);
//                    entity.getPosition().setIsTraversable(true);
                    entity.kill();
                }
            }
        }
    }

    /**
     * Recherche la proie la plus proche (Ewe ou Sheep) parmi les entit?s de la ferme.
     */
    public FarmAnimal findClosestPrey() {
        FarmAnimal closest = null;
        double minDistance = Double.MAX_VALUE;
        HashSet<Entity> entitiesSnapshot = new HashSet<>(farm.getEntitiesSet());
        for (Iterator<Entity> it = entitiesSnapshot.iterator(); it.hasNext(); ) {
            Entity e = it.next();
            if (e instanceof FarmAnimal) {
                FarmAnimal animal = (FarmAnimal) e;
                String species = animal.getSpecies();
                if (species.equals("Hen")) {
                    double distance = this.getPosition().distanceTo(animal.getPosition());
                    if (distance < minDistance) {
                        minDistance = distance;
                        closest = animal;
                    }
                }
            }
        }
        return closest;
    }

    @Override
    public String getSpecies() {
        return null;
    }

    @Override
    public int get_buying_price() throws UnauthorizedAction {
        return 0;
    }

    @Override
    public int get_selling_price() throws UnauthorizedAction {
        return 0;
    }
}

