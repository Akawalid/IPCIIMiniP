package Model.Entities;

import Model.Direction;
import Model.Exceptions.InvalidCoordinates;
import Model.Exceptions.UnauthorizedAction;
import Model.Farm;
import Model.Position.EntityMovementThread;
import Model.Position.Positionnable;
import Model.Position.Spot;
import Model.Position.FindPath;

import java.util.*;

public abstract class Entity extends Positionnable implements Comparable<Entity> {
    //This class represents all the living creatures of the game
    //They share proprieties like: move, position, name and id
    //It is abstract because we shouldn't have an animal of type that is strictly equal to entity (absurd).
    private static int idCounter = 0;
    //We need this attribute here in order to get the position of the active entity from the controller easily
    protected ArrayDeque<Spot> path; // Queue to store movements
    private EntityMovementThread thread;
    private Direction direction;
    protected final int id;
    // An entity's priority depends on its distance to the target:
    // The closer it is to the target, the higher its priority.
    // Therefore, priority is determined by the remaining distance to the target.
    // Si les bêtes bougent, alors il faut bien adapter la priorité ainsi que les méthodes qui en dépendent pour leurs mouvements.

    public Entity(Spot position){
        super(position);

        id = idCounter;
        idCounter++;

        path = new ArrayDeque<>();

        int dirX = Farm.WIDTH/2 - position.getRow();
        int dirY = Farm.HEIGHT/2 - position.getCol();
        if(dirX == 0 && dirY == 0) direction = Direction.DOWN;
        else direction = Direction.fromCoordinates(dirX, dirY);
    }

    public int getId(){
        return id;
    }
    public Direction getDirection(){return direction;}
    public abstract String getSpecies();

    //move is an abstract methode because each entity has its own way of moving.
    //move method throws InvalidCoordinates in case if the user clicks on an invalid spot such as a tree, or a rock...
    public synchronized void move() throws InvalidCoordinates {
        if (path.isEmpty()) {
            return; // No movements in the queue
        }
        //if the next spot is traversable this means that another entity just came to cross it
        //we wait until this spot becomes free, then we cross
        if(!path.peek().isTraversable()) {
            //In conflict case, we return false
            handleConflict();
        };

        // Get the next spot from the queue
        assert(!path.isEmpty());
        Spot s = path.poll();
        direction = Direction.deduceDirection(this.position, s);
        setPosition(s); // Move to the new position
    }
    protected void handleConflict(){
        /*
            We have two cases:
                Recalculate path: in blockage case, when two entities move in opposite directions, or a new
                    entities is put in the middle of the path.
                Wait
        */
        //Blockage case
        assert(path.peek() != null );
        assert (path.peek().getPositionnable()!= null);

        assert(path.peek().getPositionnable() instanceof Entity);
        if(((Entity) path.peek().getPositionnable()).path.isEmpty()){
            //recalculate path
            Spot destination = path.pop();
            FindPath.findPath(this.position, destination);
            return;
        }
        if (((Entity) path.peek().getPositionnable()).path.peek() == this.position) {
            //the greater one calculates the path by convention
            if(compareTo(((Entity) path.peek().getPositionnable())) > 0){
                Spot destination = path.pop();
                setPath(
                        FindPath.findPath(this.position, destination)
                );
            } else {
                //the other one waits
                Spot destination = ((Entity) path.peek().getPositionnable()).path.pop();
                ((Entity) path.peek().getPositionnable()).setPath(
                        FindPath.findPath(this.position, destination)
                );
            }
        }
        //here we wait;
        //No code is needed

    }
    public void setPath(ArrayDeque<Spot> q){
        path = q;
    }
    public boolean hasMovements(){return !path.isEmpty();}

    //these methods are implemented to use HashSets of Entities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id; // Assuming Entity has a unique ID field
    }
    @Override
    public int hashCode() {
        return Objects.hash(id); // Same field as used in equals()
    }
    public EntityMovementThread getThread(){return  thread;}
    public void startNewThread(){
        // We store a thread in a attribute to avoid getting unexpected errors when the user changes the path
        //of a shepherd in the middle of its movement.
        thread = new EntityMovementThread(this);
        thread.start();
    }

    @Override
    public int compareTo(Entity other) {
        //Lexicographic order (DISTANCE_TO_TARGET, ID)
        //TODO: is it the same for all entities? it is used to decide which entity goes first if two of them
        //intersect in the path
        if (path.size() - other.path.size() != 0) {
            return path.size() - other.path.size();
        }

        return this.id - other.id;
    }

    // ### Buy & Sell ###
    public abstract int get_buying_price() throws UnauthorizedAction;
    public abstract int get_selling_price() throws UnauthorizedAction;
    public void reactToAreaChange() {
        // This method is called when the spot where the entity is positioned becomes protected or unproteted
        //it is redefined in predators
    }

}
