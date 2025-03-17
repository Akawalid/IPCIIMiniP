package Model.Shepherd;

import Model.Entity;
import Model.Exceptions.InvalidCoordinates;
import Model.Farm;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Shepherd extends Entity {
    // This class represents the shepherd, which extends the Entity class.

    public Shepherd(String name) {
        super(name);
    }
}