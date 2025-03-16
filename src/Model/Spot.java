package Model;

public class Spot {
    //this represents the units of a land, each unit has a reference to the global farm to which it belongs.
    public static final int SIZE = 1;//1 * 1 metre²
    private Farm farm;
    private int x, y;
    private boolean isOccupied;
    public Spot(int x, int y, Farm farm) {
        this.x = x;
        this.y = y;
        this.farm = farm;
        isOccupied = false;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    public int getRow() {
        return x;
    }

    public int getCol() {
        return y;
    }

    public Farm getFarm() {
        return farm;
    }
}
