package org.example;

public class BankAccount {
    private String accName;
    private double bal;
    public BankAccount(String accName, double bal) {
        this.accName = accName;
        this.bal = bal;
    }
    public String getName() {
        return accName;
    }
    public void setName(String accName) {
        this.accName = accName;
    }
    public double getBalance() {
        return bal;
    }
    public void setBalance(double bal) {
        this.bal = bal;
    }
    public void deposit(double amount) {
        bal += amount;
    }
    public void withdraw(double amount) {
        if (bal >= amount) {
            bal -= amount;
        } else {
            System.out.println("Insufficient fund");
        }
    }
}
class SavingsAccount extends BankAccount {
    public SavingsAccount(String accName, double bal) {
        super(accName, bal);
    }
    @Override

    public void withdraw(double amount) {
        if (getBalance() - amount >= 100) {
            setBalance(getBalance() - amount);
        } else {
            System.out.println("Withdrawal not allowed: Account balance should be at least $100 after withdrawal");
        }
    }
}
