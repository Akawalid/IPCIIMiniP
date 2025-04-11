package Model.Predators;

import Model.Entity;
import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.FarmAnimals.FarmAnimal;
import Model.FarmAnimals.Ewe;
import Model.FarmAnimals.Sheep;
import Model.Shepherd.FindPath;
import Model.Spot;
import Model.Exceptions.InvalidCoordinates;

import java.util.*;

public class Wolf extends Predator {

    public Wolf(Spot s, Farm farm) {
        super(s, farm);
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            // 1. V�rifier et tuer toute proie adjacente (Ewe ou Sheep)
            checkAndKillPrey();

            // 2. Chercher la proie la plus proche
            FarmAnimal target = findClosestPrey();
            if (target != null) {
                // Utiliser FindPath pour calculer le chemin vers la proie
                ArrayDeque<Spot> path = FindPath.findPath(this.getPosition(), target.getPosition());
                this.setPath(path); // D�finit le chemin dans l'entit�
                if (this.hasMovements()) {
                    try {
                        // Utilise la m�thode move() pour avancer d'une case
                        this.move();
                    } catch (InvalidCoordinates e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Si aucune proie n'est d�tect�e, se d�placer al�atoirement
                List<Spot> freeSpots = new ArrayList<>();
                for (Spot neighbor : getAdjacentSpots(this.getPosition())) {
                    if (neighbor.isTraversable()) {
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
            try {
                Thread.sleep(1000); // Pause d'une seconde entre chaque cycle
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    protected void checkAndKillPrey() {
        for (Spot neighbor : getAdjacentSpots(this.getPosition())) {
            Entity entity = farm.getEntityInSpot(neighbor.getRow(), neighbor.getCol());
            if ( entity instanceof FarmAnimal) { // entity != null &&
                String species = ((FarmAnimal) entity).getSpecies();
                // Pour le wolf, la proie peut �tre une Ewe ou un Sheep
                if (species.equals("Ewe") || species.equals("Sheep")) {
                    farm.removeEntity(entity);
                    entity.getPosition().setIsTraversable(true);
                }
            }
        }
    }

    /**
     * Recherche la proie la plus proche (Ewe ou Sheep) parmi les entit�s de la ferme.
     */
    private FarmAnimal findClosestPrey() {
        FarmAnimal closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Iterator<Entity> it = farm.getEntities(); it.hasNext(); ) {
            Entity e = it.next();
            if (e instanceof FarmAnimal) {
                FarmAnimal animal = (FarmAnimal) e;
                String species = animal.getSpecies();
                if (species.equals("Ewe") || species.equals("Sheep")) {
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

    @Override //TODO
    public String getSpecies() {
        return null;
    }
    @Override //TODO
    public int get_buying_price() {
        return 0;
    }

    @Override
    public int get_selling_price() throws UnauthorizedAction {
        return 0;
    }
}
