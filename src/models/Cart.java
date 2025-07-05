package models;
import java.util.*;
import behaviors.*;
import models.errors.CashoutException;
public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void add(Product product, int qty) {
        if (qty > product.getQuantity()) {
            throw new CashoutException("Not enough stock for " + product.getName());
        }
        items.add(new CartItem(product, qty));
    }

    public void checkout(Customer customer) {
        if (items.isEmpty()) {
            throw new CashoutException("Cart is empty");
        }

        double subtotal = calculateSubtotal();
        double shippingFee = calculateShippingFee();
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new CashoutException("Customer has not enough money");
        }

        if (hasExpiredProduct()) {
            throw new CashoutException("One or more products in the cart are expired");
        }

        customer.pay(total);
        reduceStock();
        printShipmentDetails();
        printReceipt(subtotal, shippingFee, total, customer.getBalance());
    }

    private double calculateSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    private double calculateShippingFee() {
        double shippingFee = 0;
        double feePerItem = 10;
        for (CartItem item : items) {
            if (item.getProduct().isShippable()) {
                shippingFee += feePerItem * item.getQuantity();
            }
        }
        return shippingFee;
    }

    private boolean hasExpiredProduct() {
        for (CartItem item : items) {
            if (item.getProduct().isExpired()) {
                return true;
            }
        }
        return false;
    }

    private void reduceStock() {
        for (CartItem item : items) {
            item.getProduct().decreaseQuantity(item.getQuantity());
        }
    }
    private void printShipmentDetails() {
        List<Shippable> toShip = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().isShippable()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    toShip.add(item.getProduct().getShippable());
                }
            }
        }

        if (toShip.isEmpty()) return;

        System.out.println("\n** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> count = new HashMap<>();
        Map<String, Double> weights = new HashMap<>();

        for (Shippable s : toShip) {
            count.put(s.getName(), count.getOrDefault(s.getName(), 0) + 1);
            weights.put(s.getName(), weights.getOrDefault(s.getName(), 0.0) + s.getWeight());
            totalWeight += s.getWeight();
        }

        for (String name : count.keySet()) {
            System.out.println(count.get(name) + "x " + name + "\t" + weights.get(name) + "g");
        }

        System.out.println("Total package weight " + (totalWeight / 1000) + "kg\n");
    }

    private void printReceipt(double subtotal, double shipping, double total, double remainingBalance) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "\t" + (item.getQuantity() * item.getProduct().getPrice()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t\t" + subtotal);
        System.out.println("Shipping\t\t" + shipping);
        System.out.println("Amount\t\t" + total);
    }
}
