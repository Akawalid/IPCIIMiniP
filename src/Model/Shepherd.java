package Model;

import Model.Exceptions.InvalidCoordinates;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Shepherd extends Entity {
    // This class represents the shepherd, which extends the Entity class.
    private Queue<Point> path; // Queue to store movement directions

    public Shepherd(String name) {
        super(name);
        path = new ArrayDeque<>();
    }
    public void enqueueMovement(Point direction) {
        if (direction != null) {
            path.add(direction);
        }
    }
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
        int destRow = position.getRow() + direction.x;
        int destCol = position.getCol() + direction.y;

        // Check if the destination is valid
        Farm farm = position.getFarm();
        if (farm.validCoordinates(destRow, destCol)) {
            position = farm.getSpot(destRow, destCol); // Move to the new position
        } else {
            throw new InvalidCoordinates("Invalid move: Destination coordinates are invalid.");
        }
    }

    public void clearPath() {
        path.clear();
    }
    public Queue<Point> getPath() {
        return path;
    }
}