package Model.Position;

import Model.Farm;

public class Spot {
    //this represents the units of a land, each unit has a reference to the global farm to which it belongs.
    public static final int SIZE = 1;//1 * 1 metre²
    private int x, y;
    private boolean isTraversable;
    private boolean isProtectedArea;
    private Positionnable positionnable;
    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
        isTraversable = true;
        isProtectedArea = false;
        positionnable = null;
    }
    public boolean isTraversable() {
        return isTraversable;
    }
    public void setIsTraversable(boolean traversable) {
        isTraversable = traversable;
    }
    public void setIsProtectedArea(boolean protectedArea) {
        isProtectedArea = protectedArea;
        if(positionnable != null) positionnable.reactToAreaChange();
    }
    public boolean isProtectedArea() {
        return isProtectedArea;
    }
    public int getRow() {
        return x;
    }

    public int getCol() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        // 1. Check if it's the exact same object
        if (this == o) return true;

        // 2. Check if null or wrong class
        if (o == null || getClass() != o.getClass()) return false;

        // 3. Cast and compare fields
        Spot spot = (Spot) o;
        return x == spot.x && y == spot.y;
    }
    @Override
    public int hashCode(){
        return x * Farm.WIDTH + y;
    }

    public double distanceTo(Spot other) {
        int dx = this.getRow() - other.getRow();
        int dy = this.getCol() - other.getCol();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setPositionnable(Positionnable positionnable) {
        this.positionnable = positionnable;
    }
    public Positionnable getPositionnable() {
        return positionnable;
    }
}
