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
}
