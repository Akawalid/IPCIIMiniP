package Model;

import Model.FarmAnimals.FarmAnimal;
//import Model.Predators.FoxDen;
import Model.Predators.Den;
import Model.Shepherd.*;
import Model.Predators.WolfDen;

import java.util.*;

public class Farm {
    //Farm is out model, it contains the creatures and the land
    //each creature, has a reference to the spot on which it is standing.

    public static final int WIDTH = 35, HEIGHT = 20;//spots
    private HashSet<Entity> creatures;
    private ArrayList<Den> dens;
    private ArrayList<Spot> spots;
    private Bank bank;
    //this attribute represents the active entity on the farm, the one on which we want to apply operations
    private Entity selectedEntity;

    public Farm(){
        FindPath.farm = this;
        creatures = new HashSet<>();
        spots = new ArrayList<>();
        dens = new ArrayList<>();

        bank = new Bank();

        initLand();
        selectedEntity = null;
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
    public Iterator<Den> getDens(){
        return dens.iterator();
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
    public void launchMovementThread(int destRow, int destCol){
        ArrayDeque<Spot> queue = FindPath.findPath(
                selectedEntity.getPosition(),
                getSpot(destRow, destCol)
        );

        selectedEntity.setPath(queue);

        if(selectedEntity.getThread() == null || !selectedEntity.getThread().isAlive()){
            selectedEntity.startNewThread();
        }
    }
    public Entity getSelectedEntity(){return selectedEntity;}
    public void setSelectedEntity(Entity e){selectedEntity = e;}

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
                // Ignorer le spot lui-m�me
                if (i == row && j == col)
                    continue;
                // V�rifier que les coordonn�es sont valides
                if (i >= 0 && i < HEIGHT && j >= 0 && j < WIDTH) {
                    Spot candidate = getSpot(i, j);
                    if (candidate.isTraversable()) {
                        return candidate;
                    }
                }
            }
        }
        // Aucun spot libre trouv�
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
                if (animal.getState() == AgeState.DEAD)
                    // Supprime en toute sécurité l'élément actuellement itéré
                    removeEntity(it, e);

            }
        }
    }

    public void generateDens() {
        Random rand = new Random();
        int numWolfDens = rand.nextBoolean() ? 2 : 3; // g�n�re 2 ou 3 dens de loup
        int numFoxDens = rand.nextBoolean() ? 2 : 3;  // g�n�re 2 ou 3 dens de renard

        for (int i = 0; i < numWolfDens; i++) {
            Spot spot = getRandomTraversableSpot();
            WolfDen wolfDen = new WolfDen(spot, this);
            dens.add(wolfDen);
            new Thread(wolfDen).start();
        }
        for (int i = 0; i < numFoxDens; i++) {
            Spot spot = getRandomTraversableSpot();
            //FoxDen foxDen = new FoxDen(spot, this);
            //addEntity(foxDen);
            //new Thread(foxDen).start();
        }
    }

    // M�thode utilitaire pour obtenir une case traversable al�atoire
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

    public void removeEntity(Entity e){
        /** This method removes an entity from the farm
         * it is used when an entity dies or when it is removed from the farm
         */
        //remove from Spot
        e.getPosition().setIsTraversable(true);

        //remove from Farm
        creatures.remove(e);
        e.getPosition().setIsTraversable(true);
    }
    public void removeEntity(Iterator<Entity> it, Entity e){
        /** This method removes an entity from the farm
         * it is used when an entity dies or when it is removed from the farm
         */
        //remove from Spot
        e.getPosition().setIsTraversable(true);
        e.getPosition().setPositionnable(null);

        //remove from Farm
        it.remove();
    }
    public HashSet<Spot> getChunk(Spot s, int radius){
        /** This method returns a chunk of the farm
         * it is used to get the chunk of the farm that is visible on the screen
         */
        HashSet<Spot> chunk = new HashSet<>();
        for (int i = s.getRow() - radius; i <= s.getRow() + radius; i++){
            for (int j = s.getCol() - radius; j <= s.getCol() + radius; j++){
                if(i * i + j * j > radius * radius || i < 0 || i >= HEIGHT || j < 0 || j >= WIDTH){
                    continue;
                }
                chunk.add(getSpot(i, j));
            }
        }
        return chunk;
    }

}
