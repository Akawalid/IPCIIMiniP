package Model.Entities.Predators;

import Model.Entities.Entity;
import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.Entities.FarmAnimals.FarmAnimal;
import Model.Position.FindPath;
import Model.Position.Spot;
import Model.Exceptions.InvalidCoordinates;

import java.util.*;

public class Wolf extends Predator implements Runnable {

    public Wolf(Spot s, Farm farm) {
        super(s, farm);
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
            // 1. V�rifier et tuer toute proie adjacente (Ewe ou Sheep)
            checkAndKillPrey();

            // 2. Chercher la proie la plus proche
            FarmAnimal target = findClosestPrey();
            if (target != null) {
                // Utiliser FindPath pour calculer le chemin vers la proie
                ArrayDeque<Spot> path = FindPath.findPath(this.getPosition(), target.getPosition());
                this.setPath(path);
                if (this.hasMovements()) {
                    try {
                        this.move();
                    } catch (InvalidCoordinates e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Si aucune proie n'est d�tect�e, se d�placer al�atoirement
                List<Spot> freeSpots = new ArrayList<>();
                for (Spot neighbor : getAdjacentSpots(this.getPosition())) {
                    if (neighbor.isTraversable() && neighbor.getProtectedArea() == 0) {
                        freeSpots.add(neighbor);
                    }
                }
                if (!freeSpots.isEmpty()) {
                    Spot next = freeSpots.get(rand.nextInt(freeSpots.size()));
                    // Cr�er un chemin d'une seule case pour le d�placement
                    ArrayDeque<Spot> singleStep = new ArrayDeque<>();
                    singleStep.add(next);
                    this.setPath(singleStep);
                    try {
                        this.move();
                    } catch (InvalidCoordinates e) {
                        e.printStackTrace();
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
            // Pour le wolf, la proie peut �tre une Ewe ou un Sheep
            if (species.equals("Ewe") || species.equals("Sheep") || species.equals("Hen")) {
//                    farm.removeEntity(entity);
//                    entity.getPosition().setIsTraversable(true);
                entity.kill();
            }
        }
    }
}
    /**
     * Retourne la liste des cases adjacentes (haut, bas, gauche, droite) � la position actuelle.
     */
    protected List<Spot> getAdjacentSpots(Spot s) {
        List<Spot> neighbors = new ArrayList<>();
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        for (int[] d : directions) {
            int newRow = s.getRow() + d[0];
            int newCol = s.getCol() + d[1];
            // V�rifier que les indices sont dans les limites du terrain
            if (newRow >= 0 && newRow < Farm.HEIGHT && newCol >= 0 && newCol < Farm.WIDTH) {
                neighbors.add(farm.getSpot(newRow, newCol));
            }
        }
        return neighbors;
    }

    /**
     * Recherche la proie la plus proche (Ewe ou Sheep) parmi les entit�s de la ferme.
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
                if (species.equals("Ewe") || species.equals("Sheep") || species.equals("Hen")) {
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
    @Override //TODO
    public String getSpecies() {
        return "Wolf";
    }
    @Override //TODO
    public int get_buying_price() {
        throw new UnsupportedOperationException("Not supported on predator.");
    }

    @Override
    public int get_selling_price() {
        throw new UnsupportedOperationException("Not supported on predator.");
    }
}
