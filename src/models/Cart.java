package models;
import java.util.*;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void add(Product product, int qty) {
        if (qty > product.getQuantity()) {
            System.out.println("Not enough stock for " + product.getName());
            return;
        }
        items.add(new CartItem(product, qty));
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
}
