package Model;

import Model.FarmAnimals.FarmAnimal;
//import Model.Predators.FoxDen;
import Model.Shepherd.*;
import Model.Predators.WolfDen;

import java.util.*;

public class Farm {
    //Farm is out model, it contains the creatures and the land
    //each creature, has a reference to the spot on which it is standing.

    public static final int WIDTH = 15, HEIGHT = 10;//spots
    private HashSet<Entity> creatures;
    private ArrayList<Spot> spots;
    private FindPath pathFinder;
    private Bank bank;
    //this attribute represents the active entity on the farm, the one on which we want to apply operations
    private Entity selectedEntity;

    public Farm(){
        creatures = new HashSet<>();
        spots = new ArrayList<>();

        bank = new Bank();

        initLand();
        selectedEntity = null;

        pathFinder = new FindPath(this);
    }

    private void initLand(){
        //this method initiates the land, (fill).
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                spots.add(new Spot(i, j));
            }
        }
    }

    public Bank getBank(){
        return bank;
    }
    public Spot getSpot(int row, int col){
        return spots.get(row * WIDTH + col);
    }


    public boolean validCoordinates(int row, int col){
        //this method checks if the given coordinates are valid.
        return row >= 0
                && row < HEIGHT
                && col >= 0
                && col < WIDTH
                && getSpot(row, col).isTraversable();
    }

    public Iterator<Entity> getEntities(){
        //this method returns an iterator of the creatures,
        //It is a good way to ensure abstraction.
        return creatures.iterator();
    }

    public void addEntity(Entity e){
        creatures.add(e);
    }

    public Entity getEntityInSpot(int row, int col){
        //TODO:Shlag
        Spot s = getSpot(row, col);
        for(Entity e: creatures)
            if(e.getPosition() == s) {
                return e;
            }
        return null;
    }
    public Entity getSelectedEntity(){return selectedEntity;}
    public void setSelectedEntity(Entity e){selectedEntity = e;}
    //genertae a getter for pathfinder
    public FindPath getPathFinder(){return pathFinder;}

    /**
     * Returns an adjacent free spot (i.e., traversable) to the given spot.
     * The search checks the eight neighbors (horizontal, vertical, and diagonal).
     *
     * @param s the reference spot
     * @return a free adjacent spot if one is found, or null otherwise.
     */
    public Spot getAdjacentFreeSpot(Spot s) {
        int row = s.getRow();
        int col = s.getCol();

        // Parcours des 8 voisins
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Ignorer le spot lui-même
                if (i == row && j == col)
                    continue;
                // Vérifier que les coordonnées sont valides
                if (i >= 0 && i < HEIGHT && j >= 0 && j < WIDTH) {
                    Spot candidate = getSpot(i, j);
                    if (candidate.isTraversable()) {
                        return candidate;
                    }
                }
            }
        }
        // Aucun spot libre trouvé
        return null;
    }

    /**
     * Updates the age of all farm animals and removes those that are dead.
     * This method is synchronized to prevent concurrent modifications.
     */
    public synchronized void updateEntities() {
        Iterator<Entity> it = creatures.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            if (e instanceof FarmAnimal) {
                FarmAnimal animal = (FarmAnimal) e;
                animal.updateAge();
                if (animal.getState() == AgeState.DEAD) {
                    e.getPosition().setIsTraversable(true);  // Free up the cell
                    it.remove(); // Remove the dead animal from the farm
                }
            }
        }
    }


    public void removeEntity(Entity e){
        //this method removes an entity from the farm
        //it is used when an entity dies or when it is removed from the farm
        creatures.remove(e);
        e.getPosition().setIsTraversable(true);
    }

    public void generateDens() {
        Random rand = new Random();
        int numWolfDens = rand.nextBoolean() ? 2 : 3; // génère 2 ou 3 dens de loup
        int numFoxDens = rand.nextBoolean() ? 2 : 3;  // génère 2 ou 3 dens de renard

        for (int i = 0; i < numWolfDens; i++) {
            Spot spot = getRandomTraversableSpot();
            WolfDen wolfDen = new WolfDen(spot, this);
            addEntity(wolfDen);
            new Thread(wolfDen).start();
        }
        for (int i = 0; i < numFoxDens; i++) {
            Spot spot = getRandomTraversableSpot();
            //FoxDen foxDen = new FoxDen(spot, this);
            //addEntity(foxDen);
            //new Thread(foxDen).start();
        }
    }

    // Méthode utilitaire pour obtenir une case traversable aléatoire
    private Spot getRandomTraversableSpot() {
        Random rand = new Random();
        while (true) {
            int row = rand.nextInt(HEIGHT);
            int col = rand.nextInt(WIDTH);
            Spot spot = getSpot(row, col);
            if (spot.isTraversable()) {
                return spot;
            }
        }
    }

    // Dans la classe Farm
    public void initPredators() {
        generateDens();
    }

    /**
     * Retourne la liste des cases adjacentes (haut, bas, gauche, droite) de la case donnée.
     */
    public List<Spot> getAdjacentSpots(Spot s) {
        List<Spot> neighbors = new ArrayList<>();
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        for (int[] d : directions) {
            int newRow = s.getRow() + d[0];
            int newCol = s.getCol() + d[1];
            if (newRow >= 0 && newRow < Farm.HEIGHT && newCol >= 0 && newCol < Farm.WIDTH) {
                neighbors.add(getSpot(newRow, newCol));
            }
        }
        return neighbors;
    }

}
