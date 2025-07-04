package models;

public class Customer {
    private final String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public void pay(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }
}