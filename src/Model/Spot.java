package Model;

public class Spot {
    //this represents the units of a land, each unit has a reference to the global farm to which it belongs.
    public static final int SIZE = 1;//1 * 1 metre²
    private Farm farm;
    private int x, y;
    public Spot(int x, int y, Farm farm) {
        this.x = x;
        this.y = y;
        this.farm = farm;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceTo(Spot other) {
        int dx = this.getX() - other.getX();
        int dy = this.getY() - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}
