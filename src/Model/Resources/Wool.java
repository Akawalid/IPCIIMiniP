package Model.Resources;

public class Wool extends Resource{
    private static final int COOLDOWN_MAX_WOOL = 15, PRICE = 40;
    public Wool() {
        super(PRICE, COOLDOWN_MAX_WOOL);
    }

    @Override
    protected boolean get_ready_on_purchase() {
        return true;
    }

    @Override
    public String get_name() {
        return "Wool";
    }
}
