package Model.Resources;

public class Milk extends Resource{
    private final static int COOLDOWN_MAX_MILK = 10, PRICE = 30;
    public Milk() {
        super(PRICE, COOLDOWN_MAX_MILK);
    }
    @Override
    protected boolean get_ready_on_purchase() {
        return false;
    }

    @Override
    public String get_name() {
        return "Milk";
    }
}
