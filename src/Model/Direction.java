package Model;

import Model.Position.Spot;

public enum Direction {
    UP(0, -1),    // (dx, dy)
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }
    public static Direction fromCoordinates(int x, int y) {
        for (Direction direction : Direction.values()) {
            if (direction.dx * x + direction.dy * y > 0) {
                return direction;
            }
        }
        throw new Error("A direction should exist"); // or throw an exception if no matching direction is found
    }

    public static Direction deduceDirection(Spot s1, Spot s2){
        //deduce the orientation of the movement
        int dx = s2.getRow() - s1.getRow();
        int dy = s2.getCol() - s1.getCol();
        if (dx == 0 && dy == 0) {
            return null; // No movement
        }
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? DOWN : UP;
        } else {
            return dy > 0 ? RIGHT : LEFT;
        }
    }
}