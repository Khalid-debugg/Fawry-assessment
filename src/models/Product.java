package models;

public class Product {
    String name;
    double price;
    int quantity;
    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    void decreaseQuantity(int amount) {
        quantity -= amount;
    }
}
