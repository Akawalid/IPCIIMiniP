package Model;

import Model.Entities.CleanDeadEntitiesThread;
import Model.Entities.Entity;
import Model.Entities.FarmAnimals.AgeState;
import Model.Entities.FarmAnimals.FarmAnimal;
//import Model.Entities.Predators.FoxDen;
import Model.Position.FindPath;
import Model.Position.Spot;
import Model.Entities.Predators.Den;
import Model.Entities.Predators.Predator;
import Model.Entities.Predators.WolfDen;

import java.util.*;

public class Farm {
    //Farm is out model, it contains the creatures and the land
    //each creature, has a reference to the spot on which it is standing.

    //The hashmap below, corresponds to associations symbol -> isTraversable, where symbol represents a type
    // of a spot, for example, grass, wood...
    public static HashMap<Integer, Boolean> isTheTileTraversable = new HashMap<>() {{
        put(0, true);
        put(1, true);
        put(2, true);
        put(3, true);
        put(4, true);
        put(5, false);
        put(6, true);
        put(7, true);
        put(8, true);
        put(9, true);
        put(10, true);
        put(11, true);
        put(12, true);
        put(13, true);
        put(14, true);
        put(15, false);
        put(16, false);
        put(17, true);
        put(18, true);
        put(19, false);
        put(20, true);
        put(21, true);
        put(22, false);
        put(23, true);
        put(24, true);
        put(25, true);
        put(26, true);
        put(27, false);
        put(28, false);
        put(29, true);
        put(30, true);
        put(31, true);
        put(32, true);
        put(33, true);
        put(34, true);
        put(35, true);
        put(36, true);
        put(37, true);
        put(38, true);
        put(39, true);
        put(40, true);
        put(41, true);
        put(42, true);
        put(43, true);
        put(44, false);
        put(45, false);
        put(46, false);
        put(47, false);
        put(56, false);
        put(58, false);
        put(59, false);
        put(68, false);
        put(69, false);
        put(70, false);
        put(71, false);
        put(80, false);
        put(81, false);
        put(82, false);
        put(83, false);
    }};
    public static ArrayList<Integer>[][] grid1;
    private static Random random;
    static {
        initGrid();
    }

    private static void initGrid(){
        random = new Random();
        grid1 = new ArrayList[Farm.HEIGHT][Farm.WIDTH];
        for (int i = 0; i < Farm.HEIGHT; i++) {
            for (int j = 0; j < Farm.WIDTH; j++) {
                grid1[i][j] = new ArrayList<>();
                if(random.nextDouble() < 0.85){
                    grid1[i][j].add(random.nextInt(2));
                    continue;
                }
                grid1[i][j].add(2);
            }
        }

        grid1[0][0].add(0);
        grid1[0][1].add(0);
        grid1[0][2].add(1);
        grid1[0][3].add(2);
        grid1[0][4].add(1);
        grid1[0][5].add(1);
        grid1[0][6].add(2);
        grid1[0][7].add(1);
        grid1[0][8].add(1);
        grid1[0][9].add(0);
        grid1[0][10].add(0);
        grid1[0][11].add(1);
        grid1[0][12].add(1);
        grid1[0][13].add(2);
        grid1[0][14].add(2);
        grid1[0][15].add(1);
        grid1[0][16].add(14);
        grid1[0][17].add(13);

        for(int i = 0; i < 16; i++){
            grid1[1][i].add(43);
        }
        grid1[1][16].add(26);

        grid1[2][0].add(0);
        grid1[2][1].add(0);
        grid1[2][2].add(1);
        grid1[2][3].add(2);
        grid1[2][4].add(1);
        grid1[2][5].add(1);
        grid1[2][6].add(2);
        grid1[2][7].add(1);
        grid1[2][8].add(1);
        grid1[2][9].add(0);
        grid1[2][10].add(0);
        grid1[2][11].add(1);
        grid1[2][12].add(1);
        grid1[2][13].add(2);
        grid1[2][14].add(2);
        grid1[2][15].add(1);
        grid1[2][16].add(26);
        grid1[2][17].add(25);

        grid1[3][0].add(14);
        grid1[3][1].add(13);
        grid1[3][2].add(13);
        grid1[3][3].add(13);
        grid1[3][4].add(13);
        grid1[3][5].add(13);
        grid1[3][6].add(13);
        grid1[3][7].add(13);
        grid1[3][8].add(13);
        grid1[3][9].add(12);
        grid1[3][10].add(1);
        grid1[3][11].add(43);
        grid1[3][12].add(1);
        grid1[3][13].add(0);
        grid1[3][14].add(0);
        grid1[3][15].add(1);
        grid1[3][16].add(26);
        grid1[3][17].add(25);
        grid1[3][18].add(25);
        grid1[3][19].add(25);
        grid1[3][20].add(25);


        for(int i = 4; i < 13; i++){
            grid1[i][0].add(26);
            grid1[i][1].add(25);
            grid1[i][2].add(25);
            grid1[i][3].add(25);
            grid1[i][4].add(25);
            grid1[i][5].add(25);
            grid1[i][6].add(25);
            grid1[i][7].add(25);
            grid1[i][8].add(25);
            grid1[i][9].add(24);
            grid1[i][10].add(1);
            grid1[i][11].add(43);
            grid1[i][12].add(1);
            grid1[i][13].add(26);
            grid1[i][14].add(25);
            grid1[i][15].add(25);
            grid1[i][16].add(25);
            grid1[i][17].add(25);
            grid1[i][18].add(25);
            grid1[i][19].add(25);
            grid1[i][20].add(25);

            grid1[i][21].add(25);
            grid1[i][22].add(25);
            grid1[i][23].add(25);
            grid1[i][24].add(25);
            grid1[i][25].add(25);
            grid1[i][26].add(25);
            grid1[i][27].add(24);
        }

        grid1[4][13].add(0);
        grid1[4][14].add(0);
        grid1[4][15].add(1);
        grid1[4][16].add(26);
        grid1[4][17].add(25);
        grid1[4][18].add(25);
        grid1[4][19].add(25);
        grid1[4][20].add(25);

        grid1[5][13].add(14);
        grid1[5][14].add(13);
        grid1[5][15].add(13);
        grid1[5][16].add(25);
        grid1[5][17].add(25);
        grid1[5][18].add(25);
        grid1[5][19].add(25);
        grid1[5][20].add(25);

        grid1[12][6].add(24);
        grid1[12][7].add(0);
        grid1[12][8].add(1);
        grid1[12][9].add(0);

        grid1[13][0].add(38);
        grid1[13][1].add(37);
        grid1[13][2].add(37);
        grid1[13][3].add(37);
        grid1[13][4].add(37);
        grid1[13][5].add(37);
        grid1[13][6].add(36);

        grid1[11][6].add(24);
        grid1[11][7].add(0);
        grid1[11][8].add(1);
        grid1[11][9].add(0);

        grid1[10][6].add(24);
        grid1[10][7].add(1);
        grid1[10][8].add(2);
        grid1[10][9].add(2);

        grid1[9][6].add(25);
        grid1[9][7].add(37);
        grid1[9][8].add(37);
        grid1[9][9].add(36);

        grid1[10][13].add(38);
        grid1[10][14].add(37);
        grid1[10][15].add(37);
        grid1[10][16].add(37);
        grid1[10][17].add(37);
        grid1[10][18].add(37);
        grid1[10][19].add(37);
        grid1[10][20].add(37);

        grid1[11][13].add(0);
        grid1[11][14].add(1);
        grid1[11][15].add(0);
        grid1[11][16].add(2);
        grid1[11][17].add(0);
        grid1[11][18].add(1);
        grid1[11][19].add(1);
        grid1[11][20].add(2);
        grid1[11][21].add(26);

        grid1[12][13].add(43);
        grid1[12][14].add(43);
        grid1[12][15].add(43);
        grid1[12][16].add(43);
        grid1[12][17].add(43);
        grid1[12][18].add(43);
        grid1[12][19].add(43);
        grid1[12][20].add(43);
        grid1[12][21].add(26);

        grid1[13][21].add(26);
        grid1[13][22].add(25);
        grid1[13][23].add(25);
        grid1[13][24].add(25);
        grid1[13][25].add(25);
        grid1[13][26].add(25);
        grid1[13][27].add(24);

        grid1[14][21].add(26);
        grid1[14][22].add(25);
        grid1[14][23].add(25);
        grid1[14][24].add(25);
        grid1[14][25].add(25);
        grid1[14][26].add(25);
        grid1[14][27].add(24);

        grid1[15][21].add(38);
        grid1[15][22].add(37);
        grid1[15][23].add(37);
        grid1[15][24].add(37);
        grid1[15][25].add(37);
        grid1[15][26].add(37);
        grid1[15][27].add(36);

        grid1[6][23].add(0);
        grid1[6][24].add(2);
        grid1[6][25].add(0);
        grid1[6][26].add(1);
        grid1[6][27].add(1);

        grid1[7][23].add(13);
        grid1[7][24].add(13);
        grid1[7][25].add(13);
        grid1[7][26].add(13);
        grid1[7][27].add(12);

        grid1[5][23].add(0);
        grid1[5][24].add(1);
        grid1[5][25].add(2);
        grid1[5][26].add(2);
        grid1[5][27].add(1);

        grid1[4][23].add(1);
        grid1[4][24].add(2);
        grid1[4][25].add(2);
        grid1[4][26].add(1);
        grid1[4][27].add(0);

        grid1[0][22].add(12);
        grid1[1][22].add(24);
        grid1[2][22].add(24);
        grid1[3][22].add(24);
        grid1[4][22].add(24);
        grid1[5][22].add(24);
        grid1[6][22].add(24);

        grid1[0][21].add(13);
        grid1[0][20].add(13);
        grid1[0][19].add(13);
        grid1[0][18].add(13);
        grid1[1][21].add(25);
        grid1[1][20].add(25);
        grid1[1][19].add(25);
        grid1[1][18].add(25);
        grid1[1][17].add(25);
        grid1[2][21].add(25);
        grid1[2][20].add(25);
        grid1[2][19].add(25);
        grid1[2][18].add(25);
        grid1[3][21].add(25);

// Column 19 (previously row 0-8)
        grid1[19][0].add(19);
        grid1[19][1].add(19);
        grid1[19][2].add(19);
        grid1[19][3].add(19);
        grid1[19][4].add(19);
        grid1[19][5].add(19);
        grid1[19][6].add(19);
        grid1[19][7].add(19);
        grid1[19][8].add(19);
        grid1[19][9].add(19);
        grid1[19][10].add(19);
        grid1[19][11].add(19);
        grid1[19][12].add(19);
        grid1[19][13].add(19);

// Column 18 (previously row 0-8)
        grid1[18][0].add(19);
        grid1[18][1].add(19);
        grid1[18][2].add(19);
        grid1[18][3].add(19);
        grid1[18][4].add(19);
        grid1[17][4].add(7);
        grid1[18][5].add(6);
        grid1[18][6].add(7);
        grid1[18][7].add(7);
        grid1[18][8].add(8);
        grid1[18][9].add(19);
        grid1[17][9].add(8);
        grid1[18][10].add(19);
        grid1[17][10].add(19);
        grid1[18][11].add(19);
        grid1[17][11].add(19);
        grid1[18][12].add(19);
        grid1[17][12].add(6);
        grid1[16][11].add(7);
        grid1[16][10].add(7);
        grid1[18][13].add(6);
        grid1[19][14].add(6);

        grid1[17][0].add(19);
        grid1[16][0].add(19);
        grid1[17][1].add(19);
        grid1[16][1].add(19);
        grid1[15][0].add(7);
        grid1[15][1].add(7);
        grid1[17][2].add(19);
        grid1[16][2].add(6);
        grid1[17][3].add(6);

        // -1;, -1 forest
        grid1[19][34].add(19);
        grid1[18][34].add(19);
        grid1[17][34].add(19);
        grid1[16][34].add(19);
        grid1[15][34].add(19);

        grid1[19][33].add(19);
        grid1[19][32].add(19);
        grid1[19][31].add(19);
        grid1[19][30].add(19);


        grid1[18][33].add(19);
        grid1[17][33].add(8);
        grid1[17][32].add(7);
        grid1[16][33].add(20);
        grid1[15][33].add(20);
        grid1[14][34].add(7);

        grid1[18][32].add(19);
        grid1[18][31].add(8);
        grid1[18][30].add(7);
        grid1[19][29].add(20);

        grid1[19][22].add(19);
        grid1[19][21].add(8);
        grid1[19][23].add(6);
        grid1[18][22].add(7);


        //
        grid1[0][34].add(19);
        grid1[1][34].add(19);
        grid1[2][34].add(19);
        grid1[3][34].add(19);
        grid1[4][34].add(19);

        grid1[0][33].add(19);
        grid1[0][32].add(19);
        grid1[0][31].add(19);
        grid1[0][30].add(19);


        grid1[1][33].add(19);
        grid1[2][33].add(32);
        grid1[2][32].add(31);
        grid1[3][33].add(20);
        grid1[4][33].add(20);
        grid1[5][34].add(31);

        grid1[1][32].add(19);
        grid1[1][31].add(32);
        grid1[1][30].add(31);
        grid1[0][29].add(20);

        for(int i = 0; i < Farm.HEIGHT; i++){
            for(int j = 0; j < Farm.WIDTH; j++){
                if(grid1[i][j].getLast() == 25 && random.nextDouble() < 0.1){
                    grid1[i][j].clear();
                    grid1[i][j].add(random.nextInt(39, 43));
                }
                else if((grid1[i][j].getLast() == 0
                        ||
                        grid1[i][j].getLast() == 1
                        ||
                        grid1[i][j].getLast() == 2
                        ||
                        grid1[i][j].getLast() == 25)
                        &&
                        random.nextDouble() < 0.05
                ) {
                    if(random.nextInt(2) == 0 )
                        grid1[i][j].add(17);
                    else
                        grid1[i][j].add(29);
                }
            }
        }

        grid1[11][8].add(43);
        grid1[12][10].add(43);
        grid1[12][8].add(43);
        grid1[13][9].add(43);
        grid1[13][11].add(43);
        grid1[13][8].add(43);
        grid1[14][9].add(43);
        grid1[14][11].add(43);
        grid1[14][13].add(43);
        grid1[14][15].add(43);
        grid1[14][19].add(43);
        grid1[15][7].add(43);
        grid1[15][6].add(43);
        grid1[15][11].add(43);
        grid1[15][12].add(43);
        grid1[15][17].add(43);
        grid1[16][17].add(43);
        grid1[16][8].add(43);

        grid1[6][23].add(15);
        grid1[5][23].add(3);
        grid1[6][24].add(15);
        grid1[5][24].add(3);
        grid1[6][25].add(15);
        grid1[5][25].add(3);
        grid1[6][26].add(15);
        grid1[5][26].add(3);
        grid1[6][27].add(15);
        grid1[5][27].add(3);


        for(int i=0; i < 8; i++){
            grid1[10][20 - i].add(16);
            grid1[9][20 - i].add(4);
        }

        grid1[13][0].add(46);
        grid1[13][1].add(81);
        grid1[13][2].add(81);
        grid1[13][3].add(45);
        grid1[13][4].add(81);
        grid1[13][5].add(81);
        grid1[13][6].add(44);

        grid1[13][14].add(46);
        grid1[13][15].add(81);
        grid1[13][16].add(45);
        grid1[13][17].add(81);
        grid1[13][18].add(44);
    }
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
        (new CleanDeadEntitiesThread(this)).start();
    }

    private void initLand(){
        //this method initiates the land, (fill).
        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){
                Spot s = new Spot(i, j);
                boolean t = true;
                for(Integer k: grid1[i][j]){
                    if(!isTheTileTraversable.get(k)){
                        t = false;
                        break;
                    }
                }
                s.setIsTraversable(t);
                spots.add(s);
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

    public void cleanDeadEntities(){
        Iterator<Entity> it = creatures.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            if (e instanceof FarmAnimal) {
                FarmAnimal animal = (FarmAnimal) e;
                if (animal.getState() == AgeState.DEAD)
                    removeEntity(it, e);
            } else if(e instanceof Predator){
                Predator predator = (Predator) e;
                if (predator.getIsDead()){
                    removeEntity(it, e);

                }
            }
        }
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

        //if it was selected, then stop selecting it
        if(e == selectedEntity) selectedEntity = null;

        System.out.printf("%s-%d died.\n", e.getSpecies(), e.getId());
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
                if(
                        (i - s.getRow()) * (i - s.getRow())
                        +
                        (j - s.getCol()) * (j - s.getCol())
                        > radius * radius || i < 0 || i >= HEIGHT || j < 0 || j >= WIDTH
                ){
                    continue;
                }
                chunk.add(getSpot(i, j));
            }
        }
        return chunk;
    }

}
