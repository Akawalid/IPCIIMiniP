package Model;

public class Spot {
    //this represents the units of a land, each unit has a reference to the global farm to which it belongs.
    public static final int SIZE = 1;//1 * 1 metre²
    private Farm farm;
    private int x, y;
    private boolean isTraversable;
    public Spot(int x, int y, Farm farm) {
        this.x = x;
        this.y = y;
        this.farm = farm;
        isTraversable = true;
    }
    public boolean isTraversable() {
        return isTraversable;
    }
    public void setIsTraversable(boolean traversable) {
        isTraversable = traversable;
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
