package Model;

import Model.Exceptions.InvalidCoordinates;

import java.awt.*;

public abstract class Entity {
    //This class represents all the living creatures of the game
    //They share proprieties like: move, position, name and id
    //It is abstract because we shouldn't have an animal of type that is strictly equal to entity (absurd).
    private static int idCounter = 0;
    protected Spot position;
    protected final String name;
    protected final int id;
    public Entity(String name){
        //we can have entities without a position => position = null
        position = null;
        this.name = name;
        id = idCounter;
        idCounter++;
    }

    public Entity(Spot position, String name){
        this(name);
        this.position = position;
    }

    public void setPosition(Spot position){
        this.position = position;
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
    abstract public void move(Point direction) throws InvalidCoordinates;


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
