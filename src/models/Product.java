package models;

import behaviors.*;

public class Product {
    private final String name;
    private final double price;
    int quantity;
    private final Shippable shippable;
    private final Expirable expirable;
    public Product(String name, double price, int quantity) {
        this(name, price, quantity, null, null);
    }

    public Product(String name, double price, int quantity, Shippable shippable) {
        this(name, price, quantity, shippable, null);
    }

    public Product(String name, double price, int quantity, Expirable expirable) {
        this(name, price, quantity, null, expirable);
    }

    public Product(String name, double price, int quantity, Shippable shippable, Expirable expirable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shippable = shippable;
        this.expirable = expirable;
    }
    public boolean isExpired() {
        return expirable != null && expirable.isExpired();
    }

    public boolean isShippable() {
        return shippable != null;
    }
    void decreaseQuantity(int amount) {
        quantity -= amount;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Shippable getShippable() { return shippable; }
    public Expirable getExpirable() { return expirable; }
}
