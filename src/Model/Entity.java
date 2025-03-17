package Model;

import Model.Exceptions.InvalidCoordinates;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public abstract class Entity {
    //This class represents all the living creatures of the game
    //They share proprieties like: move, position, name and id
    //It is abstract because we shouldn't have an animal of type that is strictly equal to entity (absurd).
    private static int idCounter = 0;
    protected Spot position;
    private Queue<Point> path; // Queue to store movements
    public final String name;
    protected final int id;
    public Entity(String name){
        //we can have entities without a position => position = null
        position = null;
        this.name = name;
        id = idCounter;
        idCounter++;

        path = new ArrayDeque<>();

    }

    public Entity(Spot position, String name){
        this(name);
        this.position = position;
    }

    public void setPosition(Spot position){
        if(this.position != null) this.position.setIsTraversable(true);
        this.position = position;

        assert this.position != null;
        this.position.setIsTraversable(false);
    }
    public Spot getPosition(){
        return position;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return  name;
    }

    //move is an abstract methode because each entity has its own way of moving.
    //move method throws InvalidCoordinates in case if the user clicks on an invalid spot such as a tree, or a rock...
    public void move() throws InvalidCoordinates {
        if (path.isEmpty()) {
            return; // No movements in the queue
        }

        // Get the next direction from the queue
        Point direction = path.poll();

        if (position == null) {
            return; // No current position
        }

        // Calculate the destination coordinates
        int destRow = direction.x;
        int destCol = direction.y;

        Farm farm = position.getFarm();
        setPosition(farm.getSpot(destRow, destCol)); // Move to the new position
        System.out.println("row: " + destRow + ", col: " + destCol);
    }
    public void setPath(Queue<Point> q){
        path = q;
    }

    //these methods are implemented to use HashSets of Entities
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Entity)) return false;
        Entity e = (Entity) o;
        return e.id == id;
    }
    @Override
    public int hashCode(){
        return id;
    }
}
