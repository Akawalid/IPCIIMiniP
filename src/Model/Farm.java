package Model;

import Model.FarmAnimals.FarmAnimal;
import Model.Shepherd.*;

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

    //

    public void removeEntity(Entity e){
        creatures.remove(e);
    }

}
