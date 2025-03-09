package Model;

import Model.Exceptions.InvalidCoordinates;

import java.awt.*;

public class Shepherd extends Entity {
//This class represents the shepherd, which extends Entity class, because it shares a set of proprieties with the other entities.
    public Shepherd(String name){
        super(name);
    }

    public void move(Point direction) throws InvalidCoordinates {
        //We give it the destination spot, it checks if it is valid(not a traversable spot, reachable spot), then it moves to it.
        if (position == null) return;
        int destRow = position.getRow() + direction.x;
        int destCol = position.getCol() + direction.y;
        Farm fm = (position.getFarm());
        if(fm.validCoordinates(destRow, destCol)){
            position = fm.getSpot(destRow, destCol);
            return;
        }
        throw new InvalidCoordinates("Invalid move");
    }
}
