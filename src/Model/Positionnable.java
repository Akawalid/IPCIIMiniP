package Model;

public abstract class Positionnable {
    protected Spot position;
    public Positionnable(Spot position){
        assert (position != null);
        setPosition(position);
}

    public void setPosition(Spot position){
        assert (position != null);
        assert (position.isTraversable());

        if(this.position != null){
            //this.position is null only while creating the instance
            this.position.setIsTraversable(true);
            this.position.setPositionnable(null);
        }

        this.position = position;
        this.position.setPositionnable(this);
        this.position.setIsTraversable(false);
    }
    public Spot getPosition(){
        //Invariant: position != null
        assert(position != null);
        return position;
    }

    public abstract void reactToAreaChange();
}
