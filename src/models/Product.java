package models;

public class Product {
    private final String name;
    private final double price;
    int quantity;
    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    void decreaseQuantity(int amount) {
        quantity -= amount;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}
