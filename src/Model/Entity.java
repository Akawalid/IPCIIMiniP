package Model;

public class Entity {
    private static int idCounter = 0;
    private Spot position;
    private final String name;
    private final int id;
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
}
