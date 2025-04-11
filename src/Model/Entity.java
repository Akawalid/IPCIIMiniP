package Model;

import Model.Exceptions.InvalidCoordinates;
import Model.Exceptions.UnauthorizedAction;
import Model.Shepherd.FindPath;

import java.util.*;

import static Model.AgeState.MATURE;
import static Model.AgeState.OLD;

public abstract class Entity implements Comparable<Entity> {
    //This class represents all the living creatures of the game
    //They share proprieties like: move, position, name and id
    //It is abstract because we shouldn't have an animal of type that is strictly equal to entity (absurd).
    private static int idCounter = 0;
    //We need this attribute here in order to get the position of the active entity from the controller easily
    protected Spot position;
    private ArrayDeque<Spot> path; // Queue to store movements
    private EntityMovementThread thread;
    protected final int id;
    // An entity's priority depends on its distance to the target:
    // The closer it is to the target, the higher its priority.
    // Therefore, priority is determined by the remaining distance to the target.
    // TODO: Les bêtes bougent ? Si oui, alors il faut bien adapter la priorité ainsi que les méthodes qui en dépendent pour leurs mouvements.

    public Entity(Spot position){
        assert (position != null);
        setPosition(position);

        id = idCounter;
        idCounter++;

        path = new ArrayDeque<>();
    }

    public void setPosition(Spot position){
        assert (position != null);
        assert (position.isTraversable());

        if(this.position != null){
            //this.position is null only while creating the instance
            this.position.setIsTraversable(true);
            this.position.setEntity(null);
        }

        this.position = position;
        this.position.setEntity(this);
        this.position.setIsTraversable(false);
    }
    public Spot getPosition(){
        //Invariant: position != null
        assert(position != null);
        return position;
    }

    public int getId(){
        return id;
    }
    public abstract String getSpecies();

    //move is an abstract methode because each entity has its own way of moving.
    //move method throws InvalidCoordinates in case if the user clicks on an invalid spot such as a tree, or a rock...
    public void move() throws InvalidCoordinates {
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
        setPosition(path.poll()); // Move to the new position
    }
    private void handleConflict(){
        /*
            We have two cases:
                Recalculate path: in blockage case, when two entities move in opposite directions, or a new
                    entities is put in the middle of the path.
                Wait
        */
        //Blockage case

        assert (path.peek().getEntity()!= null);
        if(path.peek().getEntity().path.isEmpty()){
            //recalculate path
            Spot destination = path.pop();
            FindPath.findPath(this.position, destination);
            return;
        }
        if (path.peek().getEntity().path.peek() == this.position) {
            //the greater one calculates the path by convention
            if(compareTo(path.peek().getEntity()) > 0){
                Spot destination = path.pop();
                setPath(
                        FindPath.findPath(this.position, destination)
                );
            } else {
                //the other one waits
                Spot destination = path.peek().getEntity().path.pop();
                path.peek().getEntity().setPath(
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
    public int getPathSize(){return path.size();}
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
    public abstract int get_buying_price();
    public abstract int get_selling_price() throws UnauthorizedAction;

}
