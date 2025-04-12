package Model.Resources;

public class Egg extends Resource{
    private static final int COOLDOWN_MAX_EGG = 10, PRICE = 10;
    public Egg() {
        super(PRICE, COOLDOWN_MAX_EGG);
    }
    @Override
    protected boolean get_ready_on_purchase() {
        return false;
    }

    @Override
    public String get_name() {
        return "Egg";
    }
}
