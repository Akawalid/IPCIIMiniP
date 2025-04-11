package Model;

import Model.FarmAnimals.Ewe;
import Model.FarmAnimals.Hen;
import Model.FarmAnimals.Sheep;
import Model.Resources.Egg;
import Model.Resources.Milk;
import Model.Resources.Resource;
import Model.Resources.Wool;
import Model.Shepherd.Shepherd;

public class Bank {

    // ### Resource Selling ### //
    private final static int PRICE_EWE = 200;
    private final static int PRICE_SHEEP = 100;
    private final static int PRICE_HEN = 40;
    private final static int SALARY_SHEPHERD = 80;

    //This class Handles the bidget of the Shepherd
    private static final int INITIAL_BALANCE = 2000;
    private int balance;//dollars

    public Bank(){
        balance = INITIAL_BALANCE;
    }
    public static int get_price(Entity e) {
        if (e instanceof Ewe) {
            return PRICE_EWE;
        } else if (e instanceof Sheep) {
            return PRICE_SHEEP;
        } else if (e instanceof Hen) {
            return PRICE_HEN;
        } else if (e instanceof Shepherd) {
            return SALARY_SHEPHERD;
        } else {
            throw new NoSuchMethodError("Method get_price for another entity than ewe, sheep, hen, or shepherd is not defined.");
        }
    }

    public static int get_price(Resource r) {
        if (r instanceof Milk) {
            return 20;
        } else if (r instanceof Wool) {
            return 20;
        } else if (r instanceof Egg) {
            return 10;
        } else {
            throw new NoSuchMethodError("Method get_price for another resource than wool, egg, or milk is not defined.");
        }
    }



    public void deposit(int amount){
        balance += amount;
    }
    public void withdraw(int amount){
        balance -= amount;
    }
    public int getBalance(){
        return balance;
    }

}
