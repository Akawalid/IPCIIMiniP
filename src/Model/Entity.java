package Model;

import Model.Exceptions.InvalidCoordinates;
import Model.Exceptions.UnauthorizedAction;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class Entity {
    //This class represents all the living creatures of the game
    //They share proprieties like: move, position, name and id
    //It is abstract because we shouldn't have an animal of type that is strictly equal to entity (absurd).
    private static int idCounter = 0;
    //We need this attribute here in order to get the position of the active entity from the controller easily
    protected Spot position;
    private Queue<Spot> path; // Queue to store movements
    protected final int id;

    public Entity(Spot position){
        assert position != null;
        this.position = position;

        id = idCounter;
        idCounter++;

        path = new ArrayDeque<>();
    }

    public void setPosition(Spot position){
        if(this.position != null) this.position.setIsTraversable(true);
        this.position = position;

        assert this.position != null;
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
        // Get the next spot from the queue
        Spot nextSpot = path.poll();
        setPosition(nextSpot); // Move to the new position
    }
    public void setPath(Queue<Spot> q){
        path = q;
    }
     public Queue<Spot> getPath(){return null;}

    //these methods are implemented to use HashSets of Entities
    @Override
    public boolean equals(Object o){
        //we can not put multiple entities on the same spot.
        return position.equals(o);
    }
    @Override
    public int hashCode(){
        //This makes it easy for us to look for an entity by its position, it comes handy
        // in the controller
        return position.hashCode();
    }

    public Thread getThread(int id){
        //TODO (Coxy, Izma): this method should be abstract, therefore, it should be implemented in all the subclasses.

        //Each entity may have multiple threads that agitate on them, the id serves to identify the thread
        //of a concrete entity if multiple one's exists, otherwise, it is ignored.
        return null;
    };
}
