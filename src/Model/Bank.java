package Model;

import java.util.HashSet;

public class Bank {
    //This class Handles the bidget of the Shepherd
    private static final int INITIAL_BALANCE = 2000;
    private int balance;//dollars

    private HashSet<Entity> liveStock;
    public Bank(){

        balance = INITIAL_BALANCE;
        liveStock = new HashSet<>();
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
