package Model;

public class Bank {

    //This class Handles the bidget of the Shepherd
    private static final int INITIAL_BALANCE = 20000;
    private int balance;//dollars

    public Bank(){
        balance = INITIAL_BALANCE;
    }

    public static int getIinitialBalance() { return INITIAL_BALANCE;}

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
