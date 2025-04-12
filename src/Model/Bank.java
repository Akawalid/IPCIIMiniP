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

    //This class Handles the bidget of the Shepherd
    private static final int INITIAL_BALANCE = 2000;
    private int balance;//dollars

    public Bank(){
        balance = INITIAL_BALANCE;
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
