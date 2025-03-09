package Model;

public class Bank {
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
}
