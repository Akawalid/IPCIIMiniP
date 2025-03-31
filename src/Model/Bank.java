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
    public static int get_price(Entity e) { //throws InvalidState {
        return switch (e) {
            case Ewe ewe -> PRICE_EWE;
            case Sheep sheep -> PRICE_SHEEP;
            case Hen hen -> PRICE_HEN;
            case Shepherd shepherd -> SALARY_SHEPHERD;
            case null, default -> throw new NoSuchMethodError("Method get_price for another entity than ewe, sheep, hen, or shepherd is not defined.");
        };
        //throw new InvalidState("We can get the price of an Ewe, a Sheep, a Hen or the salary of a Sheperd only.");
    }

    public static int get_price(Resource r){
        return switch (r) {
            case Milk milk -> 20;
            case Wool wool -> 20;
            case Egg egg -> 10;
            case null, default -> throw new NoSuchMethodError("Method get_price for another resource than wool, egg, or milk is not defined.");
        };
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
